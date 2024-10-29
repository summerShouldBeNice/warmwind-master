package top.warmwind.master.core.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.warmwind.master.system.entity.SysUser;

/**
 * SpringSecurity工具类
 *
 * @author warmwind
 * @since 2024-10-29 下午9:30
 */
public class SecurityUtil {

    /**
     * 获取当前登录的用户
     * @return SysUser
     */
    public static SysUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object object = authentication.getPrincipal();
            if (object instanceof SysUser) {
                return (SysUser) object;
            }
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     * @return userId
     */
    public static Integer getLoginUserId() {
        SysUser loginSysUser = getLoginUser();
        return loginSysUser == null ? null : loginSysUser.getId();
    }
}
