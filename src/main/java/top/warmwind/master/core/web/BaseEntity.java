package top.warmwind.master.core.web;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * Entity 基类
 *
 * @author warmwind
 * @since 2024-10-18 下午6:05
 */
@Data
public class BaseEntity {

    @Schema(name = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(name = "创建时间")
    private Date createTime;

    @Schema(name = "更新时间")
    private Date updateTime;

    @Schema(name = "删除标记 0否 1是")
    @TableLogic
    private Integer deleted;

}
