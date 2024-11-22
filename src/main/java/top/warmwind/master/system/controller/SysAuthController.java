package top.warmwind.master.system.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.warmwind.master.core.basic.BaseController;
import top.warmwind.master.core.config.SysConfigProperties;
import top.warmwind.master.core.constants.SysRedisConstants;
import top.warmwind.master.core.utils.JwtSubject;
import top.warmwind.master.core.utils.JwtUtil;
import top.warmwind.master.core.web.ApiResult;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.enums.AccountStatus;
import top.warmwind.master.system.enums.LoginType;
import top.warmwind.master.system.param.SysLoginParam;
import top.warmwind.master.system.result.LoginResult;
import top.warmwind.master.system.service.*;

import java.util.List;

/**
 * @author warmwind
 * @since 2024-10-24 下午10:47
 */
@Tag(name = "系统认证接口", description = "系统登录注册认证接口")
@RestController
@RequestMapping("/api/v1/sys/auth")
public class SysAuthController extends BaseController {

    @Value("${spring.profiles.active}")
    private String profile;

    private StringRedisTemplate stringRedisTemplate;

    private SysUserService sysUserService;

    private SysUserRoleService sysUserRoleService;

    private SysRoleMenuService sysRoleMenuService;

    private SysConfigProperties sysConfigProperties;

    private SysLoginRecordService sysLoginRecordService;

    @Autowired
    private SysAuthController(StringRedisTemplate stringRedisTemplate,
                              SysUserService sysUserService,
                              SysConfigProperties sysConfigProperties,
                              SysLoginRecordService sysLoginRecordService,
                              SysUserRoleService sysUserRoleService,
                              SysRoleMenuService sysRoleMenuService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.sysUserService = sysUserService;
        this.sysConfigProperties = sysConfigProperties;
        this.sysLoginRecordService = sysLoginRecordService;
        this.sysUserRoleService = sysUserRoleService;
        this.sysRoleMenuService = sysRoleMenuService;
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody SysLoginParam param, HttpServletRequest request) {
        String today = DatePattern.PURE_DATE_FORMAT.format(new DateTime());
        String verifyCode = stringRedisTemplate.opsForValue().get(SysRedisConstants.LOGIN_CAPTCHA_VERIFY_CODE + today + param.getCodeKey());
        if (StrUtil.isBlank(verifyCode)) {
            return fail(LoginType.VERIFY_CODE_EXPIRED.getLabel(), null);
        }
        if (!verifyCode.equalsIgnoreCase(param.getCode())) {
            return fail(LoginType.VERIFY_CODE_ERROR.getLabel(), null);
        }
        String username = param.getUsername();
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        if (sysUser == null) {
            sysLoginRecordService.saveAsync(username, LoginType.ERROR.getValue(), LoginType.ERROR.getLabel(), request);
            return fail(LoginType.ERROR.getLabel(), null);
        }
        if (AccountStatus.LOCKED.getValue().equals(sysUser.getAccountStatus())) {
            sysLoginRecordService.saveAsync(username, LoginType.LOCK.getValue(), LoginType.LOCK.getLabel(), request);
            return fail(LoginType.LOCK.getLabel(), null);
        }
        if (!sysUserService.comparePassword(sysUser.getPassword(), param.getPassword())) {
            sysLoginRecordService.saveAsync(username, LoginType.PASSWORD_ERROR.getValue(), LoginType.PASSWORD_ERROR.getLabel(), request);
            return fail(LoginType.PASSWORD_ERROR.getLabel(), null);
        }

        List<SysRole> roles = sysUserRoleService.getRoleListByUserId(sysUser.getId());
        List<SysMenu> authority = sysRoleMenuService.getMenuListByUserId(sysUser.getId());
        LoginResult result = new LoginResult();
        result.setRoles(roles);
        result.setAuthorities(authority);

        sysLoginRecordService.saveAsync(username, LoginType.SUCCESS.getValue(),null, request);
        // 签发TOKEN
        String accessToken = JwtUtil.buildToken(sysConfigProperties.getIssuer(),
                new JwtSubject(username),
                sysConfigProperties.getTokenExpireTime(),
                sysConfigProperties.getBase64EncodedKey());

        result.setAccessToken(accessToken);
        return success(LoginType.SUCCESS.getLabel(), result);
    }

}
