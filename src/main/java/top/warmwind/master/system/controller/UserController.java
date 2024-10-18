package top.warmwind.master.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warmwind
 * @since 2024-09-03 下午5:28
 */
@RestController
@RequestMapping("/usr")
public class UserController {

    @GetMapping("/qjf")
    public String user() {
        return "邱俊峰";
    }

}
