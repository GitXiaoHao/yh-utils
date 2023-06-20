package top.yh.utils.string;

/**
 * @author yuhao
 * @date 2023/3/31
 **/
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
