package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import top.warmwind.master.core.basic.BaseEntity;

import java.util.Date;

/**
 * 系统菜单表
 *
 * @author warmwind
 * @since 2024-11-01 下午7:24
 */
@Data
@TableName("sys_menu")
@Schema(description = "系统用户表")
public class SysMenu extends BaseEntity implements GrantedAuthority {

    @Schema(description = "上级id, 0是顶级")
    private Integer parentId;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单路由地址")
    private String path;

    @Schema(description = "菜单组件地址")
    private String component;

    @Schema(description = "菜单类型, 0菜单, 1按钮")
    private Integer menuType;

    @Schema(description = "排序号")
    private Integer sortNumber;

    @Schema(description = "权限标识")
    private String authority;

    @Schema(description = "打开位置")
    private String target;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "图标颜色")
    private String color;

    @Schema(description = "是否隐藏, 0否, 1是(仅注册路由不显示左侧菜单)")
    private Integer hide;

    @Schema(description = "侧栏菜单选中的path")
    private String active;

    @Schema(description = "路由元信息")
    private String meta;

}
