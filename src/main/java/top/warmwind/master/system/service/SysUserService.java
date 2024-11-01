package top.warmwind.master.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.warmwind.master.system.entity.SysUser;

/**
 * 系统用户服务接口
 *
 * @author warmwind
 * @since 2024-09-03 下午5:28
 */
public interface SysUserService extends IService<SysUser>, UserDetailsService {

    /**
     * 根据账号查询用户
     *
     * @param username 账号
     * @return SysUser
     */
    SysUser getSysUserByUsername(String username);
}
