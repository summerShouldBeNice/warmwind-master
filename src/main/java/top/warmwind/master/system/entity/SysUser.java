package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.warmwind.master.core.basic.BaseEntity;
import top.warmwind.master.system.enums.AccountStatus;

import java.util.Collection;
import java.util.List;

/**
 * 用户
 *
 * @author warmwind
 * @since 2024-08-07 下午4:03
 */
@Data
@TableName("sys_user")
@Schema(description = "系统用户表")
public class SysUser extends BaseEntity implements UserDetails {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "邮箱验证状态")
    private Integer emailVerifyStatus;

    @Schema(description = "用户账号状态")
    private Integer accountStatus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return AccountStatus.NORMAL.equals(accountStatus);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
