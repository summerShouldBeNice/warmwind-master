package top.warmwind.master.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.entity.SysUserRole;

import java.util.List;

/**
 * 系统用户角色服务接口
 *
 * @author warmwind
 * @since 2024-09-03 下午5:28
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 使用用户id查询当前用户关联的角色列表
     * @param sysUserId 系统用户id
     * @return List<SysRole>
     */
    List<SysRole> getRoleListByUserId(Integer sysUserId);

}
