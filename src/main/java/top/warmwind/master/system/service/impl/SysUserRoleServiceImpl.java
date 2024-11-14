package top.warmwind.master.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.warmwind.master.core.exception.AccountRetrievalException;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.entity.SysUser;
import top.warmwind.master.system.entity.SysUserRole;
import top.warmwind.master.system.mapper.SysUserMapper;
import top.warmwind.master.system.mapper.SysUserRoleMapper;
import top.warmwind.master.system.service.SysRoleService;
import top.warmwind.master.system.service.SysUserRoleService;
import top.warmwind.master.system.service.SysUserService;

import java.util.List;
import java.util.Objects;

/**
 * 系统用户角色服务实现类
 *
 * @author warmwind
 * @since 2024-11-01 下午4:59
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Override
    public List<SysRole> getRoleListByUserId(Integer sysUserId) {
        return baseMapper.selectRoleListByUserId(sysUserId);
    }

}
