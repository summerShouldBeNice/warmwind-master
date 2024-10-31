package top.warmwind.master.core.web;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应结果
 *
 * @author warmwind
 * @since 2024-10-18 下午3:53
 */
@Schema(name = "响应结果")
public class ApiResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "状态码")
    private Integer code;

    @Schema(name = "状态信息")
    private String message;

    @Schema(name = "返回数据")
    private T data;

    @Schema(name = "错误信息")
    private String error;

    public ApiResult() {
    }

    public ApiResult(Integer code) {
        this(code, null);
    }

    public ApiResult(Integer code, String message) {
        this(code, message, null);
    }

    public ApiResult(Integer code, String message, T data) {
        this(code, message, data, null);
    }

    public ApiResult(Integer code, String message, T data, String error) {
        setCode(code);
        setMessage(message);
        setData(data);
        setError(error);
    }

    public Integer getCode() {
        return this.code;
    }

    public ApiResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return this.error;
    }

    public ApiResult<T> setError(String error) {
        this.error = error;
        return this;
    }

}
