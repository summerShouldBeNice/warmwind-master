package top.warmwind.master.core.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import top.warmwind.master.core.annotation.OperationRecord;
import top.warmwind.master.core.constants.SysConstants;
import top.warmwind.master.system.entity.SysOperationRecord;
import top.warmwind.master.system.service.SysOperationRecordService;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 系统操作记录切面
 *
 * @author warmwind
 * @since 2024-07-25 下午3:20
 */
@Aspect
@Component
public class OperationRecordAspect {

    private SysOperationRecordService operationRecordService;

    @Autowired
    public OperationRecordAspect(SysOperationRecordService operationRecordService) {
        this.operationRecordService = operationRecordService;
    }

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(top.warmwind.master.core.annotation.OperationRecord)")
    public void operationRecord(){
    }

    @Before("operationRecord()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "operationRecord()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        saveOperationRecord(joinPoint, result, null);
    }

    @AfterReturning(pointcut = "operationRecord()", returning = "error")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable error){
        saveOperationRecord(joinPoint, null, error);
    }

    /**
     * 保存操作记录
     * @param joinPoint
     * @param result
     * @param error
     */
    public void saveOperationRecord(JoinPoint joinPoint, Object result, Throwable error){
        SysOperationRecord record = new SysOperationRecord();
        // 记录操作耗时
        if (startTime.get() != null) {
            record.setSpendTime(System.currentTimeMillis() - startTime.get());
        }
        // 记录请求地址、请求方式、ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (attributes == null ? null : attributes.getRequest());
        if (request != null) {
            record.setRequestUrl(request.getRequestURI());
            record.setRequestMethodType(request.getMethod());
            UserAgent ua = UserAgentUtil.parse(JakartaServletUtil.getHeaderIgnoreCase(request, "User-Agent"));
            record.setOs(ua.getOs().toString() + " " + ua.getOsVersion());
            record.setDevice(ua.getPlatform().toString());
            record.setBrowser(ua.getBrowser().toString());
            record.setIp(JakartaServletUtil.getClientIP(request));
        }
        // 记录异常信息
        if (error != null) {
            record.setStatus(1);
            record.setError(StrUtil.sub(error.toString(), 0, SysConstants.ERROR_MAX_LENGTH));
        }
        // 记录模块名、操作功能、请求方法、请求参数、返回结果
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        record.setCallingMethod(joinPoint.getTarget().getClass().getName() + "." + signature.getName());
        Method method = signature.getMethod();
        if (method != null) {
            OperationRecord operationRecord = method.getAnnotation(OperationRecord.class);
            if (operationRecord != null) {
                // 记录操作功能
                record.setDescription(getDescription(method, operationRecord));
                // 记录操作模块
                record.setModule(getModule(method, operationRecord));
                // 记录备注
                if (StrUtil.isNotEmpty(operationRecord.comments())) {
                    record.setComments(operationRecord.comments());
                }
                // 记录请求参数
                if (operationRecord.param() && request != null) {
                    record.setRequestParams(StrUtil.sub(getParams(joinPoint, request), 0, SysConstants.REQUEST_PARAMS_MAX_LENGTH));
                }
                // 记录请求结果
                if (operationRecord.result() && result != null) {
                    record.setResult(StrUtil.sub(JSON.toJSONString(result), 0, SysConstants.RESULT_MAX_LENGTH));
                }
            }
        }
        operationRecordService.saveAsync(record);
    }

    /**
     * 获取操作模块
     *
     * @param method         Method
     * @param operationRecord   OperationLog
     * @return String
     */
    private String getModule(Method method, OperationRecord operationRecord) {
        if (StrUtil.isNotEmpty(operationRecord.module())) {
            return operationRecord.module();
        }
        Tag tag = method.getDeclaringClass().getAnnotation(Tag.class);
        if (tag != null && StrUtil.isNotEmpty(tag.name())) {
            return tag.name();
        }
        return null;
    }

    /**
     * 获取操作功能
     *
     * @param method            Method
     * @param operationRecord   OperationLog
     * @return String
     */
    private String getDescription(Method method, OperationRecord operationRecord) {
        if (StrUtil.isNotEmpty(operationRecord.value())) {
            return operationRecord.value();
        }
        Tag tag = method.getDeclaringClass().getAnnotation(Tag.class);
        if (tag != null && StrUtil.isNotEmpty(tag.description())) {
            return tag.description();
        }
        return null;
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint JoinPoint
     * @param request   HttpServletRequest
     * @return String
     */
    private String getParams(JoinPoint joinPoint, HttpServletRequest request) {
        String params;
        Map<String, String> paramsMap = JakartaServletUtil.getParamMap(request);
        if (paramsMap.keySet().size() > 0) {
            params = JSON.toJSONString(paramsMap);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Object arg : joinPoint.getArgs()) {
                if (ObjectUtil.isNull(arg)
                        || arg instanceof MultipartFile
                        || arg instanceof HttpServletRequest
                        || arg instanceof HttpServletResponse) {
                    continue;
                }
                sb.append(JSON.toJSONString(arg)).append(" ");
            }
            params = sb.toString();
        }
        return params;
    }

}
