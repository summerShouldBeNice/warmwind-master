package top.warmwind.master.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.mapper.SysMenuMapper;
import top.warmwind.master.system.service.SysMenuService;

import java.util.List;

/**
 * 系统菜单服务接口实现类
 *
 * @author warmwind
 * @since 2024-11-14 下午2:34
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

}
