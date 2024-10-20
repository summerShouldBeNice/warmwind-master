package top.warmwind.master.core.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import top.warmwind.master.core.constants.SysConstants;
import top.warmwind.master.system.entity.User;

import java.util.List;

/**
 * Controller基类
 *
 * @author warmwind
 * @since 2024-09-03 下午5:36
 */
public class BaseController {

    /**
     * 获取登录的用户信息
     * @return User
     */
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object object = authentication.getPrincipal();
            if (object instanceof User) {
                return (User) object;
            }
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     * @return userId
     */
    public Integer getLoginUserId() {
        User loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

    /**
     * 返回成功
     * @return ApiResult
     */
    public ApiResult<?> success() {
        return new ApiResult<>(SysConstants.RESULT_SUCCESS_CODE, SysConstants.RESULT_SUCCESS_MSG);
    }

    /**
     * 返回成功
     * @param message 状态信息
     * @return ApiResult
     */
    public ApiResult<?> success(String message) {
        return success().setMessage(message);
    }

    /**
     * 返回成功
     * @param data 返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> success(T data) {
        return new ApiResult<>(SysConstants.RESULT_SUCCESS_CODE, SysConstants.RESULT_SUCCESS_MSG, data);
    }

    /**
     * 返回成功
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public <T> ApiResult<T> success(String message, T data) {
        return success(data).setMessage(message);
    }

    /**
     * 返回分页查询数据
     *
     * @param list  当前页数据
     * @param total 总数量
     * @return ApiResult
     */
    public <T> ApiResult<PageResult<T>> success(List<T> list, Long total) {
        return success(new PageResult<>(list, total));
    }

    /**
     * 返回分页查询数据
     *
     * @param iPage IPage
     * @return ApiResult
     */
    public <T> ApiResult<PageResult<T>> success(IPage<T> iPage) {
        return success(iPage.getRecords(), iPage.getTotal());
    }

    /**
     * 返回失败
     *
     * @return ApiResult
     */
    public ApiResult<?> fail() {
        return new ApiResult<>(SysConstants.RESULT_ERROR_CODE, SysConstants.RESULT_ERROR_MSG);
    }

    /**
     * 返回失败
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public ApiResult<?> fail(String message) {
        return fail().setMessage(message);
    }

    /**
     * 返回失败
     *
     * @param data 返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> fail(T data) {
        return fail(SysConstants.RESULT_ERROR_MSG, data);
    }

    /**
     * 返回失败
     *
     * @param message 状态信息
     * @param data    返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> fail(String message, T data) {
        return new ApiResult<>(SysConstants.RESULT_ERROR_CODE, message, data);
    }

    /**
     * 请求参数的空字符串转为null
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    
}
