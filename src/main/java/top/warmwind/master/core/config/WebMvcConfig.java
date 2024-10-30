package top.warmwind.master.core.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.warmwind.master.core.constants.SysConstants;
import top.warmwind.master.core.interceptor.LogInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * WebMvc配置, 拦截器、资源映射等
 *
 * @author warmwind
 * @since 2024-07-23 下午11:25
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private SysConfig sysConfig;

    /**
     * 支持跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .exposedHeaders(SysConstants.TOKEN_HEADER_NAME)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 配置全局消息转换器
     * @param converters
     */
    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    //     FastJsonConfig config = new FastJsonConfig();
    //     config.setDateFormat(sysConfig.getTimeFormat());
    //     config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
    //     config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
    //     converter.setFastJsonConfig(config);
    //     converter.setDefaultCharset(StandardCharsets.UTF_8);
    //     converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
    //     converters.add(0, converter);
    // }

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
