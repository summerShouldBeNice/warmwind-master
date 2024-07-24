package top.warmwind.master.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 日志拦截器
 *
 * @author warmwind
 * @since 2024-07-23 下午11:23
 */
public class LogInterceptor implements HandlerInterceptor {

    private final static String MDC_TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(MDC_TRACE_ID);
        MDC.put(MDC_TRACE_ID, traceId);
        return true;
    }
    private String getMDCTraceId() {
        long currentTime = System.nanoTime();
        return String.join("_", MDC_TRACE_ID, String.valueOf(currentTime));
    }
}
