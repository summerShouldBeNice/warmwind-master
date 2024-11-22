package top.warmwind.master.system.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.entity.SysUser;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 登录结果
 *
 * @author warmwind
 * @since 2024-11-08 上午10:36
 */
@Data
@Schema(description = "登录结果")
public class LoginResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "用户信息")
    private SysUser sysUser;

    @Schema(description = "角色列表")
    private List<SysRole> roles;

    @Schema(description = "权限列表")
    private List<SysMenu> authorities;
}
