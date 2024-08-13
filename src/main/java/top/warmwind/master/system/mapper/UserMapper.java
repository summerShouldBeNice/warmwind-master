package top.warmwind.master.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.warmwind.master.system.entity.User;

/**
 * @author warmwind
 * @since 2024-08-07 下午4:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
