package top.warmwind.master.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.warmwind.master.system.entity.SysOperationRecord;
import top.warmwind.master.system.mapper.SysOperationRecordMapper;
import top.warmwind.master.system.service.SysOperationRecordService;

/**
 * 系统操作记录服务实现类
 *
 * @author warmwind
 * @since 2024-10-29 下午9:11
 */
@Service
public class SysOperationRecordServiceImpl extends ServiceImpl<SysOperationRecordMapper, SysOperationRecord>
        implements SysOperationRecordService {

    @Async
    @Override
    public void saveAsync(SysOperationRecord sysOperationRecord) {
        save(sysOperationRecord);
    }
}
