package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.warmwind.master.core.basic.BaseEntity;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author warmwind
 * @since 2024-08-07 下午4:03
 */
@Data
@TableName("sys_user")
@Schema(name = "系统用户表")
public class SysUser extends BaseEntity implements UserDetails {

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "密码")
    private String password;

    @Schema(name = "头像")
    private String avatar;

    @Schema(name = "手机号")
    private String phone;

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "邮箱是否验证（0否，1是）")
    private Integer emailVerified;

    @Schema(name = "用户账号状态")
    private Integer status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
