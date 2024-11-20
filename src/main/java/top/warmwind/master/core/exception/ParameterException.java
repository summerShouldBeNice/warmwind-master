package top.warmwind.master.core.exception;

import top.warmwind.master.core.constants.SysConstants;

import java.io.Serial;

/**
 * @author warmwind
 * @since 2024-11-20 下午6:13
 */
public class ParameterException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;

    public ParameterException() {
        this(SysConstants.RESULT_ERROR_MSG);
    }

    public ParameterException(String message) {
        this(SysConstants.RESULT_ERROR_CODE, message);
    }

    public ParameterException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ParameterException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ParameterException(Integer code, String message, Throwable cause,
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
