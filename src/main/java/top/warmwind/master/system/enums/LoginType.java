package top.warmwind.master.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import top.warmwind.master.core.exception.EnumValueNotExistException;

import java.util.Arrays;

/**
 * 登录类型枚举
 *
 * @author warmwind
 * @since 2024-11-08 下午2:07
 */
public enum LoginType {

    /** 登录成功 */
    SUCCESS(0, "登录成功"),
    /** 登录失败 */
    ERROR(1, "账号不存在"),
    /** 账户已被锁定 */
    LOCK(2, "账户已被锁定"),
    /** 验证码过期 */
    VERIFY_CODE_EXPIRED(3, "验证码过期"),
    /** 验证码错误 */
    VERIFY_CODE_ERROR(4, "验证码错误"),
    /** 密码错误 */
    PASSWORD_ERROR(5, "密码错误"),
    /** 续签token */
    REFRESH(6, "续签token");

    @EnumValue
    private final Integer value;

    @JsonValue
    private final String label;

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    LoginType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static LoginType getByValue(String value) {
        return Arrays.stream(LoginType.values())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举值不存在"));
    }

    public static LoginType getByLabel(String label) {
        return Arrays.stream(LoginType.values())
                .filter(item -> item.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举标签不存在"));
    }
}
