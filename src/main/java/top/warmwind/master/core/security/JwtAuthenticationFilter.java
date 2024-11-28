package top.warmwind.master.core.security;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.warmwind.master.core.config.SysConfigProperties;
import top.warmwind.master.core.constants.SysConstants;
import top.warmwind.master.core.exception.AccountRetrievalException;
import top.warmwind.master.core.utils.JwtSubject;
import top.warmwind.master.core.utils.JwtUtil;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.enums.AccountStatus;
import top.warmwind.master.system.enums.LoginType;
import top.warmwind.master.system.service.SysLoginRecordService;
import top.warmwind.master.system.service.SysRoleMenuService;
import top.warmwind.master.system.service.SysUserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * jwt权限认真过滤器
 *
 * @author warmwind
 * @since 2024-11-01 下午4:50
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private SysConfigProperties sysConfigProperties;

    private SysUserService sysUserService;

    private SysRoleMenuService sysRoleMenuService;

    private SysLoginRecordService sysLoginRecordService;

    @Autowired
    public JwtAuthenticationFilter(SysConfigProperties sysConfigProperties,
                                   SysUserService sysUserService,
                                   SysRoleMenuService sysRoleMenuService,
                                   SysLoginRecordService sysLoginRecordService) {
        this.sysConfigProperties = sysConfigProperties;
        this.sysUserService = sysUserService;
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysLoginRecordService = sysLoginRecordService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = JwtUtil.getAccessToken(request);
        if (StrUtil.isNotBlank(accessToken)) {
            // 解析token
            Claims claims = JwtUtil.parseToken(accessToken, sysConfigProperties.getBase64EncodedKey());
            JwtSubject jwtSubject = JwtUtil.getJwtSubject(claims, JwtSubject.class);
            SysUser user = sysUserService.getSysUserByUsername(jwtSubject.getUsername());
            List<SysMenu> authorities = sysRoleMenuService.getMenuListByUserId(user.getId());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // token续约
            long expiration = (claims.getExpiration().getTime() - new Date().getTime()) / 1000 / 60;
            if (expiration < sysConfigProperties.getTokenRefreshTime()) {
                String refreshToken = JwtUtil.buildToken(sysConfigProperties.getIssuer(),
                        jwtSubject,
                        sysConfigProperties.getTokenExpireTime(),
                        sysConfigProperties.getBase64EncodedKey());
                response.addHeader(SysConstants.TOKEN_HEADER_NAME, refreshToken);
                sysLoginRecordService.saveAsync(user.getUsername(), LoginType.REFRESH.getValue(), null, request);
            }
        }
        filterChain.doFilter(request, response);
    }
}
