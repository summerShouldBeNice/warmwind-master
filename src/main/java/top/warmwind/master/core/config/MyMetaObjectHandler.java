package top.warmwind.master.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.warmwind.master.core.utils.SecurityUtil;

/**
 * mybatis-plus 实现自动填充
 *
 * @author warmwind
 * @since 2024-11-08 下午2:21
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "create_by", int.class, SecurityUtil.getLoginUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "update_by", int.class, SecurityUtil.getLoginUserId());
    }
}
