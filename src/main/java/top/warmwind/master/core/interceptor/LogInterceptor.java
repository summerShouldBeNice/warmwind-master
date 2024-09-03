package top.warmwind.master.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 日志拦截器，为每个线程新增一个traceId方便定位异常
 *
 * @author warmwind
 * @since 2024-07-23 下午11:23
 */
public class LogInterceptor implements HandlerInterceptor {

    private final static String MDC_TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceIDStr = getMDCTraceId();
        MDC.put(MDC_TRACE_ID, traceIDStr);
        return true;
    }

    /**
     * 生成traceId
     * @return String
     */
    private String getMDCTraceId() {
        long currentTime = System.nanoTime();
        return String.join("_", MDC_TRACE_ID, String.valueOf(currentTime));
    }
}
