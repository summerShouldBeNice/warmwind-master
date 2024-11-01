package top.warmwind.master.core.constants;

/**
 * 系统常量
 *
 * @author warmwind
 * @since 2024-07-23 下午11:27
 */
public class SysConstants {

    /**
     * token通过header传递的名称
     */
    public static final String TOKEN_HEADER_NAME = "Authorization";

    /**
     * token通过参数传递的名称
     */
    public static final String TOKEN_PARAM_NAME = "access_token";

    /**
     * token认证类型
     */
    public static final String TOKEN_TYPE = "Bearer";

    /**
     * 响应成功码
     */
    public static final int RESULT_SUCCESS_CODE = 200;

    /**
     * 响应失败码
     */
    public static final int RESULT_ERROR_CODE = 500;

    /**
     * 响应失败信息
     */
    public static final String RESULT_SUCCESS_MSG = "操作成功";

    /**
     * 响应失败信息
     */
    public static final String RESULT_ERROR_MSG = "操作失败";

    /**
     * 请求参数最大长度
     */
    public static final int REQUEST_PARAMS_MAX_LENGTH = 1000;

    /**
     * 响应结果最大长度
     */
    public static final int RESULT_MAX_LENGTH = 1000;

    /**
     * 错误信息最大长度
     */
    public static final int ERROR_MAX_LENGTH = 1000;
}
