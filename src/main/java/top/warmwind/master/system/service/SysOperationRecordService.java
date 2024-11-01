package top.warmwind.master.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.warmwind.master.system.entity.SysOperationRecord;

/**
 * 操作记录服务接口
 *
 * @author warmwind
 * @since 2024-10-29 下午9:10
 */
public interface SysOperationRecordService extends IService<SysOperationRecord> {

    /**
     * 异步添加
     *
     * @param sysOperationRecord SysOperationRecord
     */
    void saveAsync(SysOperationRecord sysOperationRecord);
}
