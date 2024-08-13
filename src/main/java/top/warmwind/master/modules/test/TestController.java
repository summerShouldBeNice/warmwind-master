package top.warmwind.master.modules.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.warmwind.master.core.annotation.OperationLog;
import top.warmwind.master.system.entity.User;
import top.warmwind.master.system.mapper.UserMapper;

import java.util.List;

/**
 * @author warmwind
 * @since 2024-07-23 下午10:12
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @OperationLog
    @GetMapping("/hello")
    public List<?> hello() {
//        return "hello world";
        return userMapper.selectList(null);
    }
}
