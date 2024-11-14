package top.warmwind.master.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRoleMenu;
import top.warmwind.master.system.mapper.SysRoleMenuMapper;
import top.warmwind.master.system.service.SysRoleMenuService;

import java.util.List;

/**
 * 系统角色菜单服务实现类
 *
 * @author warmwind
 * @since 2024-11-14 下午3:37
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements SysRoleMenuService {

    @Override
    public List<SysMenu> getMenuListByUserId(Integer sysUserId) {
        return baseMapper.selectMenuListByUserId(sysUserId);
    }
}
