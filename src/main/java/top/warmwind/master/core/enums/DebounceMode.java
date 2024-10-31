package top.warmwind.master.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import top.warmwind.master.core.exception.EnumValueNotExistException;
import top.warmwind.master.system.enums.SysUserStatus;

import java.util.Arrays;

/**
 * 防抖模式
 *
 * @author warmwind
 * @since 2024-10-30 下午9:37
 */
public enum DebounceMode {

    /** 全局防抖 */
    ALL(0, "全局"),

    /** 用户级别防抖 */
    USER(1, "用户");

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

    DebounceMode(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static DebounceMode getByValue(String value) {
        return Arrays.stream(DebounceMode.values())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举值不存在"));
    }

    public static DebounceMode getByLabel(String label) {
        return Arrays.stream(DebounceMode.values())
                .filter(item -> item.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new EnumValueNotExistException("枚举标签不存在"));
    }
}
