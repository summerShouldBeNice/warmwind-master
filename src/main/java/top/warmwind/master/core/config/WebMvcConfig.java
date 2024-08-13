package top.warmwind.master.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.warmwind.master.core.constants.SystemConstants;
import top.warmwind.master.core.interceptor.LogInterceptor;

/**
 * WebMvc配置, 拦截器、资源映射等
 *
 * @author warmwind
 * @since 2024-07-23 下午11:25
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 支持跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .exposedHeaders(SystemConstants.TOKEN_HEADER_NAME)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 将LogInterceptor拦截器注册到Spring容器中
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LogInterceptor logInterceptor = new LogInterceptor();
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }

}
