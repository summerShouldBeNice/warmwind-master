package top.warmwind.master.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import top.warmwind.master.core.exception.EnumValueNotExistException;

import java.util.Arrays;

/**
 * 系统用户账号邮箱验证状态枚举
 *
 * @author warmwind
 * @since 2024-11-01 下午6:21
 */
public enum EmailVerifyStatus {

    /** 未验证 */
    UNVERIFIED(0, "未验证"),
    /** 已验证 */
    VERIFIED(1, "已验证");

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

    EmailVerifyStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static EmailVerifyStatus getByValue(String value) {
        return Arrays.stream(EmailVerifyStatus.values())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举值不存在"));
    }

    public static EmailVerifyStatus getByLabel(String label) {
        return Arrays.stream(EmailVerifyStatus.values())
                .filter(item -> item.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举标签不存在"));
    }
}
