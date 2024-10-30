package top.warmwind.master.core.annotation;

import java.lang.annotation.*;

/**
 * 防抖参数注解
 *
 * @author warmwind
 * @since 2024-10-30 下午5:15
 */
@Documented
@Target({ElementType.METHOD,ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DebounceParam {
}
