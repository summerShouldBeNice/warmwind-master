package top.warmwind.master.core.basic;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * entity基类
 *
 * @author warmwind
 * @since 2024-10-18 下午6:05
 */
@Data
@Schema(description = "entity基类")
public class BaseEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    private Integer id;

    @Schema(description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新人")
    @TableField(fill = FieldFill.INSERT)
    private Integer updateBy;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "删除标记 0否 1是")
    @TableLogic
    private Integer isDeleted;

}
