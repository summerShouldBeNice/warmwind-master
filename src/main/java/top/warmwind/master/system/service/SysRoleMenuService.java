package top.warmwind.master.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRoleMenu;

import java.util.List;

/**
 * 系统角色菜单服务接口
 *
 * @author warmwind
 * @since 2024-11-14 下午3:36
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据用户id查询当前用户关联的菜单列表
     * @param sysUserId
     * @return
     */
    List<SysMenu> getMenuListByUserId(Integer sysUserId);
}
