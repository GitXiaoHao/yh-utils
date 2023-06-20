package top.yh.utils.encryption.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yuhao
 * @description Encryption and decryption 加密解密的md5
 * @date 2023/6/20 00:30
 **/
public class EadMd5Utils {
    private EadMd5Utils() {
    }

    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String code) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    code.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        // 16进制数字
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = new StringBuilder("0" + md5code);
        }
        return String.valueOf(md5code);
    }

    /**
     * 可逆的的加密解密方法；两次是解密，一次是加密
     *
     * @param code 加密解密内容
     * @return
     */
    public static String convertMd5(String code) {

        char[] a = code.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

}
