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
import top.warmwind.master.system.enums.AccountStatus;
import top.warmwind.master.system.mapper.SysUserMapper;
import top.warmwind.master.system.service.SysRoleService;
import top.warmwind.master.system.service.SysUserService;

import java.util.Objects;

/**
 * 系统用户服务实现类
 *
 * @author warmwind
 * @since 2024-11-01 下午4:59
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    private SysRoleService sysRoleService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SysUserServiceImpl(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public SysUser getSysUserByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            throw new AccountRetrievalException("用户名不能为空");
        }
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUsername, username);
        SysUser sysUser = getOne(wrapper);
        if (Objects.isNull(sysUser)) {
            throw new AccountRetrievalException("用户不存在");
        }
        if (AccountStatus.LOCKED.getValue().equals(sysUser.getAccountStatus())) {
            throw new AccountRetrievalException("账户已被锁定");
        }
        return sysUser;
    }

    @Override
    public boolean comparePassword(String dbPassword, String inputPassword) {
        return bCryptPasswordEncoder.matches(inputPassword, dbPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getSysUserByUsername(username);
    }
}
