package top.warmwind.master.core.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * param基类
 *
 * @author warmwind
 * @since 2024-10-18 下午6:23
 */
@Data
@Schema(name = "param基类")
public class BaseParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "分页查询页码")
    private Long page;

    @Schema(name = "分页查询每页数量")
    private Long limit;
}
