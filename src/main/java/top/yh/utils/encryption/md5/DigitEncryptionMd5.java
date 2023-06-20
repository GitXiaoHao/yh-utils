package top.yh.utils.encryption.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yuhao
 * @description Digit encryption位数md5加密
 * @date 2023/6/20 00:31
 **/
public final class DigitEncryptionMd5 {
    private DigitEncryptionMd5() {
    }
    public static final int THIRTY_TWO_LENGTH = 32;
    public static final int SIXTEEN_LENGTH = 16;
    /**
     * 入参是需要加密的文本和限制的长度
     *
     * @param code   加密内容
     * @param length 加密长度
     * @return
     */
    public static String md5ToLength(String code, int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(code.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte value : b) {
                i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.substring(0, length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
