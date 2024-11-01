package top.warmwind.master.core.exception;

import top.warmwind.master.core.constants.SysConstants;

import java.io.Serial;

/**
 * 账户信息检索异常
 *
 * @author warmwind
 * @since 2024-11-01 下午6:07
 */
public class AccountRetrievalException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;

    public AccountRetrievalException() {
        this(SysConstants.RESULT_ERROR_MSG);
    }

    public AccountRetrievalException(String message) {
        this(SysConstants.RESULT_ERROR_CODE, message);
    }

    public AccountRetrievalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AccountRetrievalException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public AccountRetrievalException(Integer code, String message, Throwable cause,
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
