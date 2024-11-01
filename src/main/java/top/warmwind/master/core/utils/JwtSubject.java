package top.warmwind.master.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Jwt载体
 *
 * @author warmwind
 * @since 2024-11-01 下午3:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSubject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

}
