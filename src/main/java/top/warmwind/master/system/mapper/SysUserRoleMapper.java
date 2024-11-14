package top.warmwind.master.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.warmwind.master.system.entity.SysRole;
import top.warmwind.master.system.entity.SysUserRole;

import java.util.List;

/**
 * 系统用户角色Mapper
 *
 * @author warmwind
 * @since 2024-11-14 下午3:39
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 获取角色列表
     * @param sysUserId
     * @return List<SysRole>
     */
    List<SysRole> selectRoleListByUserId(Integer sysUserId);

}
