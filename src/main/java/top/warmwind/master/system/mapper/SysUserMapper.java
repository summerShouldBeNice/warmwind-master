package top.warmwind.master.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.warmwind.master.system.entity.SysUser;

import java.util.List;

/**
 * 系统用户Mapper
 *
 * @author warmwind
 * @since 2024-08-07 下午4:14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
