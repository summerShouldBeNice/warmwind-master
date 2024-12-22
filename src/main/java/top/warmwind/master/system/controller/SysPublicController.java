package top.warmwind.master.system.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.pig4cloud.captcha.SpecCaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.warmwind.master.core.basic.BaseController;
import top.warmwind.master.core.constants.SysRedisConstants;
import top.warmwind.master.core.web.ApiResult;
import top.warmwind.master.system.result.CaptchaResult;

import java.util.concurrent.TimeUnit;

/**
 * 系统公开接口
 *
 * @author warmwind
 * @since 2024-10-30 下午10:29
 */
@Tag(name = "公共接口", description = "不用登录就能访问的接口")
@RestController
@RequestMapping("${api}/sys/public")
public class SysPublicController extends BaseController {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysPublicController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Operation(summary = "图形验证码")
    @GetMapping("/captcha/{key}")
    public ApiResult<?> captcha(@PathVariable("key") String key) {
        String today = DatePattern.PURE_DATE_FORMAT.format(new DateTime());
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        stringRedisTemplate.opsForValue().set(SysRedisConstants.LOGIN_CAPTCHA_VERIFY_CODE + today + key, specCaptcha.text().toLowerCase(), 5, TimeUnit.MINUTES);
        return success(new CaptchaResult(specCaptcha.toBase64()));
    }

    @Operation(summary = "测试接口")
    @GetMapping("/test")
    public ApiResult<?> test() {
        return success("测试接口");
    }

}
