package top.warmwind.master.core.security;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.warmwind.master.core.config.SysConfigProperties;
import top.warmwind.master.core.exception.AccountRetrievalException;
import top.warmwind.master.core.utils.JwtSubject;
import top.warmwind.master.core.utils.JwtUtil;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.enums.AccountStatus;
import top.warmwind.master.system.service.SysUserService;

import java.io.IOException;

/**
 * @author warmwind
 * @since 2024-11-01 下午4:50
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private SysConfigProperties sysConfigProperties;
    private SysUserService sysUserService;

    @Autowired
    public JwtAuthenticationFilter(SysConfigProperties sysConfigProperties, SysUserService sysUserService) {
        this.sysConfigProperties = sysConfigProperties;
        this.sysUserService = sysUserService;
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
            if (!AccountStatus.LOCKED.getValue().equals(user.getAccountStatus().getValue())) {
                throw new AccountRetrievalException("账户已被锁定");
            }
        }
        filterChain.doFilter(request, response);
    }
}
