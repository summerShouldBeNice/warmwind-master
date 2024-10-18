package top.warmwind.master.core.web;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author warmwind
 * @since 2024-10-18 下午6:23
 */
@Data
public class BaseParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    @Schema(name = "分页查询页码")
    private Long page;

    @TableField(exist = false)
    @Schema(name = "分页查询每页数量")
    private Long limit;
}
