package top.warmwind.master.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.warmwind.master.system.entity.SysRole;

/**
 * 系统角色服务接口
 *
 * @author warmwind
 * @since 2024-11-01 下午5:24
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     *
     * @param sysUserId 系统用户id
     * @return SysRole
     */
    SysRole selectByUserId(Integer sysUserId);
}
