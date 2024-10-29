package top.warmwind.master.core.exception;

import top.warmwind.master.core.constants.SysConstants;

import java.io.Serial;

/**
 * 业务异常
 *
 * @author warmwind
 * @since 2024-10-18 下午5:20
 */
public class BizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;

    public BizException() {
        this(SysConstants.RESULT_ERROR_MSG);
    }

    public BizException(String message) {
        this(SysConstants.RESULT_ERROR_CODE, message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BizException(Integer code, String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
