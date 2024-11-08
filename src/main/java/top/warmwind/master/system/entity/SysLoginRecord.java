package top.warmwind.master.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.warmwind.master.core.basic.BaseEntity;

/**
 * 系统登录日志
 *
 * @author warmwind
 * @since 2024-11-08 下午2:05
 */
@Data
@Schema(description = "系统登录日志表")
@TableName("sys_login_record")
public class SysLoginRecord extends BaseEntity {

    @Schema(description = "用户账号")
    private String username;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "设备名称")
    private String device;

    @Schema(description = "浏览器类型")
    private String browser;

    @Schema(description = "ip地址")
    private String ip;

    @Schema(description = "操作类型, 0登录成功, 1登录失败, 2退出登录, 3续签token")
    private Integer loginType;

    @Schema(description = "备注")
    private String comments;

}
