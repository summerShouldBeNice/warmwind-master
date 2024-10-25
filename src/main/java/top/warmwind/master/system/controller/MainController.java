package top.warmwind.master.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.warmwind.master.core.basic.BaseController;
import top.warmwind.master.system.entity.SysUser;

/**
 * @author warmwind
 * @since 2024-10-24 下午10:47
 */
@Tag(name = "系统登录注册认证接口")
@RestController
@RequestMapping("${api}")
public class MainController extends BaseController {

    @GetMapping("/test")
    public SysUser test() {
        return new SysUser();
    }
}
