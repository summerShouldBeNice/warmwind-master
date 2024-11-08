package top.warmwind.master.system.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.warmwind.master.system.entity.SysMenu;
import top.warmwind.master.system.entity.SysRole;

import java.util.List;

/**
 * 登录结果
 *
 * @author warmwind
 * @since 2024-11-08 上午10:36
 */
@Data
@Schema(description = "登录结果")
public class LoginResult {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "角色列表")
    private List<SysRole> roles;

    @Schema(description = "权限列表")
    private List<SysMenu> authorities;
}
