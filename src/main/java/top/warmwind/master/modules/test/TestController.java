package top.warmwind.master.modules.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warmwind
 * @since 2024-07-23 下午10:12
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
