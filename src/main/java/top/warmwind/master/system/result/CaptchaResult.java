package top.warmwind.master.system.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 验证码返回结果
 *
 * @author warmwind
 * @since 2024-11-01 下午11:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "验证码返回结果")
public class CaptchaResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "图形验证码base64数据")
    private String base64;
}
