package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统角色菜单关联表
 *
 * @author warmwind
 * @since 2024-11-01 下午7:25
 */
@Data
@TableName("sys_role_menu")
@Schema(description = "系统角色菜单关联表")
public class SysRoleMenu {

    @Schema(description = "主键编号")
    private String id;

    @Schema(description = "角色主键编号")
    private String roleId;

    @Schema(description = "系统菜单编号")
    private String menuId;
}
