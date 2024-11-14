package top.warmwind.master.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRoleMenu;

import java.util.List;

/**
 * 系统角色菜单Mapper
 *
 * @author warmwind
 * @since 2024-11-14 下午3:35
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据用户id获取菜单列表
     * @return
     */
    List<SysMenu> selectMenuListByUserId(Integer sysUserId);
}
