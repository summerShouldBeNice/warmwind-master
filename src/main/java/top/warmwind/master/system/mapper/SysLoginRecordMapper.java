package top.warmwind.master.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.warmwind.master.system.entity.SysLoginRecord;

/**
 * 系统登录记录Mapper
 *
 * @author warmwind
 * @since 2024-11-08 下午2:36
 */
@Mapper
public interface SysLoginRecordMapper extends BaseMapper<SysLoginRecord> {
}
