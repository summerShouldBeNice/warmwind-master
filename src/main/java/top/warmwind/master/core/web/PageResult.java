package top.warmwind.master.core.web;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.parameters.P;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回结果
 *
 * @author warmwind
 * @since 2024-10-18 下午5:55
 */
@Schema(description = "分页结果")
public class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "当前页数据")
    private List<T> list;

    @Schema(name = "总数量")
    private Long total;

    public PageResult() {}

    public PageResult(List<T> list) {
        this(list, null);
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

}
