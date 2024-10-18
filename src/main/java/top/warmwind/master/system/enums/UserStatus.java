package top.warmwind.master.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import top.warmwind.master.system.exception.EnumValueNotExistException;

import java.util.Arrays;

/**
 * @author warmwind
 * @since 2024-09-03 下午6:19
 */
public enum UserStatus {

    /** 正常 */
    NORMAL(0, "正常"),
    /** 锁定 */
    LOCKED(1, "已锁定");

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

    UserStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static UserStatus getByValue(String value) {
        return Arrays.stream(UserStatus.values())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举值不存在"));
    }

    public static UserStatus getByLabel(String label) {
        return Arrays.stream(UserStatus.values())
                .filter(item -> item.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举标签不存在"));
    }
}
