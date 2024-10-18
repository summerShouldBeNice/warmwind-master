package top.warmwind.master.core.config;

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
public class SysConfig {

    private String timeFormat = "yyyy-MM-dd HH:mm:ss";

}
