package top.warmwind.master.system.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.warmwind.master.core.basic.BaseParam;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统登录参数
 *
 * @author warmwind
 * @since 2024-10-30 下午9:57
 */
@Data
@Schema(description = "系统登录参数")
public class SysLoginParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "验证码")
    private String captcha;

    @Schema(description = "验证码key")
    private String captchaKey;

    @Schema(description = "手机号验证码")
    private String phoneCaptcha;

    @Schema(description = "登录方式")
    private Integer loginType;

}
