package top.warmwind.master.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import top.warmwind.master.system.entity.SysLoginRecord;

/**
 * 系统登录记录服务接口
 *
 * @author warmwind
 * @since 2024-11-08 下午2:37
 */
public interface SysLoginRecordService extends IService<SysLoginRecord> {

    /**
     * 异步添加
     *
     * @param SysLoginRecord sysLoginRecord
     */
    /**
     * 异步添加
     *
     * @param username  用户名
     * @param type      登录类型
     * @param comments  备注
     * @param request   HttpServletRequest
     */
    void saveAsync(String username, Integer type, String comments, HttpServletRequest request);

}
