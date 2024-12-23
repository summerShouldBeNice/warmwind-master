package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author warmwind
 * @since 2024-10-24 下午10:33
 */
@Data
@TableName("sys_user_role")
@Schema(description = "系统角色权限表")
public class SysUserRole {

    @Schema(description = "主键编号")
    private String id;

    @Schema(description = "用户主键编号")
    private String userId;

    @Schema(description = "角色主键编号")
    private String roleId;

}
