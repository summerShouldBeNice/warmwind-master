package top.warmwind.master.core.utils;

import java.util.List;
import java.util.Optional;

/**
 * @author warmwind
 * @since 2024-11-28 下午6:15
 */
public class CollectionUtil {

    public static <E> boolean hasDuplication(Optional<List<E>> optionalList) {
        return optionalList.map(list -> list.stream().distinct().count() != list.size())
                .orElse(false);
    }
}
