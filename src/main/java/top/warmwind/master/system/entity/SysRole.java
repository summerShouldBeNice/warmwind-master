package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import top.warmwind.master.core.basic.BaseEntity;

import java.io.Serializable;

/**
 * @author warmwind
 * @since 2024-09-03 下午5:48
 */
@Data
@TableName("sys_user")
@Schema(description = "系统角色表")
public class SysRole extends BaseEntity {

    @Schema(description = "角色标识")
    private String roleCode;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "备注")
    private String comments;

}
