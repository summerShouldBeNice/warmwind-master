package top.warmwind.master.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author warmwind
 * @since 2024-10-18 下午4:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t1")
    public Date test() {
        System.out.println(new Date());
        return new Date();
    }

    @GetMapping("/t2")
    public String test2() {
        return "test2";
    }
}
