package top.warmwind.master.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.mapper.SysRoleMapper;
import top.warmwind.master.system.service.SysRoleService;

/**
 * 系统角色服务实现类
 *
 * @author warmwind
 * @since 2024-11-01 下午5:25
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {



    @Override
    public SysRole selectByUserId(Integer sysUserId) {
        return null;
    }
}