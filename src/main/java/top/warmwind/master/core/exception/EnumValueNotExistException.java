package top.warmwind.master.core.exception;

import top.warmwind.master.core.constants.SysConstants;

import java.io.Serial;

/**
 * @author warmwind
 * @since 2024-09-03 下午6:23
 */
public class EnumValueNotExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;

    public EnumValueNotExistException() {
        this(SysConstants.RESULT_ERROR_MSG);
    }

    public EnumValueNotExistException(String message) {
        this(SysConstants.RESULT_ERROR_CODE, message);
    }

    public EnumValueNotExistException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public EnumValueNotExistException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public EnumValueNotExistException(Integer code, String message, Throwable cause,
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
