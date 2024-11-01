package top.warmwind.master.core.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统配置
 *
 * @author warmwind
 * @since 2024-10-18 下午4:01
 */
@Data
@ConfigurationProperties(prefix = "config")
public class SysConfigProperties {

    @Schema(name = "token加密base64秘钥")
    private String base64EncodedKey;

    @Schema(name = "token过期时间, 单位秒")
    private Long tokenExpireTime = 60 * 60 * 24L;

    @Schema(name = "token快要过期自动刷新时间, 单位分钟")
    private int tokenRefreshTime = 30;

    @Schema(name = "token签发人")
    private String issuer;

}
