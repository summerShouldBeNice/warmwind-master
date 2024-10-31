package top.warmwind.master.core.annotation;

import top.warmwind.master.core.enums.DebounceMode;
import top.warmwind.master.core.exception.BizException;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 防抖注解
 *
 * @author warmwind
 * @since 2024-10-30 下午12:58
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Debounce {
    /**
     * 前缀 ()
     */
    String prefix();

    /**
     * 分隔符
     */
    String delimiter() default "&";

    /**
     * 防抖模式
     * @return
     */
    DebounceMode mode() default DebounceMode.ALL;

    /**
     * 等待时间
     */
    long waitTime() default 5000;

    /**
     * 租约时间
     */
    long leaseTime() default 10000;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 异常类型
     */
    Class<? extends RuntimeException> exception() default BizException.class;

    /**
     * 异常消息提示
     */
    String message() default "操作频繁，请稍后重试！";
}
