package top.warmwind.master.system.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.warmwind.master.core.basic.BaseController;
import top.warmwind.master.core.web.ApiResult;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.param.SysLoginParam;

/**
 * @author warmwind
 * @since 2024-10-24 下午10:47
 */
@Tag(name = "系统认证接口", description = "系统登录注册认证接口")
@RestController
@RequestMapping("${api}")
public class SysAuthController extends BaseController {

    @Value("${spring.profiles.active}")
    private String profile;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody SysLoginParam param, HttpServletRequest request) {
        // String today = DatePattern.PURE_DATE_FORMAT.format(new DateTime());
        // String verifyCode = timedCache.get(today + param.getCodeKey(), false);
        // if (StrUtil.isBlank(verifyCode)) {
        //     String message = "验证码已过期";
        //     return fail(message, null);
        // }
        // if (!verifyCode.equalsIgnoreCase(param.getCode())) {
        //     String message = "验证码错误";
        //     return fail(message, null);
        // }
        //
        // String username = param.getUsername();
        // Integer tenantId = param.getTenantId();
        // User user = userService.getByUsername(username, tenantId);
        // if (user == null) {
        //     String message = "账号不存在";
        //     loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, tenantId, request);
        //     return fail(message, null);
        // }
        // if (SystemConstants.USER_STATUS_FAIL.equals(user.getStatus())) {
        //     String message = "账户已被停用，如要开启，请联系管理员";
        //     loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, tenantId, request);
        //     return fail(message, null);
        // }
        // if (!userService.comparePassword(user.getPassword(), param.getPassword())) {
        //     String message = "密码错误";
        //     loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, tenantId, request);
        //     return fail(message, null);
        // }
        //
        // loginRecordService.saveAsync(username, LoginRecord.TYPE_LOGIN, null, tenantId, request);
        //
        //
        // // 登录参数有OPENID并且登录账户没有绑定OPENID时 为登录用户绑定OPENID
        // String openId = param.getOpenId();
        // if (!StrUtil.isBlank(openId) && StrUtil.isBlank(user.getOpenId())) {
        //     userService.updateOpenId(user.getTenantId(), user.getUserId(), openId);
        // }
        //
        // // 设置用户学生证信息
        // if (SystemConstants.USER_TYPE_STUDENT.equals(user.getUserType())) {
        //     user.setCard(cardService.getByUserId(user.getUserId()));
        // }
        //
        // // 签发TOKEN
        // String accessToken = JwtUtil.buildToken(new JwtSubject(username, tenantId),
        //         configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        // return success("登录成功", new LoginResult(accessToken, user));
        return null;
    }

}
