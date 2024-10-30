package top.warmwind.master.core.aspect;

import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import top.warmwind.master.core.annotation.Debounce;
import top.warmwind.master.core.annotation.DebounceParam;
import top.warmwind.master.core.constants.SysRedisConstants;
import top.warmwind.master.core.redis.RedisLockService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.UUID;

/**
 * 防抖切面
 *
 * @author warmwind
 * @since 2024-10-30 下午12:59
 */
@Aspect
@Component
public class DebounceAspect {

    private final RedisLockService redisLockService;

    @Autowired
    public DebounceAspect(RedisLockService redisLockService) {
        this.redisLockService = redisLockService;
    }

    @Around("execution(public * * (..)) && @annotation(top.warmwind.master.core.annotation.Debounce)")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Debounce debounce = method.getAnnotation(Debounce.class);
        Class<? extends Exception> exception = debounce.exception();
        if (StrUtil.isEmpty(debounce.prefix())) {
            throw exception.getDeclaredConstructor(String.class).newInstance("防抖前缀不能为空");
        }
        final String lockKey = getLockKey(joinPoint);
        boolean success = redisLockService.getLock(SysRedisConstants.DEBOUNCE + lockKey,
                debounce.waitTime(),
                debounce.leaseTime());
        if (Boolean.FALSE.equals(success)) {
            throw exception.getDeclaredConstructor(String.class).newInstance(debounce.message());
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw exception.getDeclaredConstructor(String.class).newInstance(throwable.getMessage());
        } finally {
            redisLockService.releaseLock(SysRedisConstants.DEBOUNCE + lockKey);
        }
    }

    public static String getLockKey(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Debounce debounce = method.getAnnotation(Debounce.class);
        final Object[] args = joinPoint.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            final DebounceParam debounceParam = parameters[i].getAnnotation(DebounceParam.class);
            if (Objects.nonNull(debounceParam)) {
                sb.append(debounce.delimiter()).append(args[i]);
            }
        }
        if (StrUtil.isEmpty(sb.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final DebounceParam debounceParam = field.getAnnotation(DebounceParam.class);
                    if (Objects.nonNull(debounceParam)) {
                        field.setAccessible(true);
                        sb.append(debounce.delimiter()).append(ReflectionUtils.getField(field, object));
                    }
                }
            }
        }
        return debounce.prefix() + sb;
    }

}
