package top.warmwind.master.core.utils;

/**
 * 校验工具类
 *
 * @author warmwind
 * @since 2024-11-20 下午6:17
 */
public class VerifyUtil {

    /**
     * 默认分隔符
     */
    private static final String DEFAULT_DELIMITER = ",";

    /**
     * 匹配文本
     * @param matchStr 待匹配的文本
     * @param originStr 源文本
     * @return boolean
     */
    public static boolean matchText(String matchStr, String originStr) {
        String[] suffixArr = originStr.split(DEFAULT_DELIMITER);
        for (String s : suffixArr) {
            if (matchStr.toLowerCase().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 匹配文本
     * @param matchStr 待匹配的文本
     * @param originStr 源文本
     * @param delimiter 分隔符
     * @return boolean
     */
    public static boolean matchText(String matchStr, String originStr, String delimiter) {
        String[] suffixArr = originStr.split(delimiter);
        for (String s : suffixArr) {
            if (matchStr.toLowerCase().equals(s)) {
                return true;
            }
        }
        return false;
    }

}
