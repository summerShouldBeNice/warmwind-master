package top.warmwind.master.test;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.warmwind.master.core.annotation.OperationRecord;

import java.util.Date;

/**
 * @author warmwind
 * @since 2024-10-18 下午4:55
 */
@RestController
@RequestMapping("/test")
@Tag(name = "测试", description = "测试接口")
public class TestController {

    @GetMapping("/t1")
    public Date test() {
        System.out.println(new Date());
        return new Date();
    }

    @OperationRecord
    @GetMapping("/t2")
    public String test2() {
        return "test2";
    }
}
