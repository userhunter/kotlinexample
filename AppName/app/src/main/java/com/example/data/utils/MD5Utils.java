package com.example.data.utils;

/**
 * autour: bobbylu
 * date: 2019/1/23 on 19:42
 */
public class MD5Utils {

    private static final String HEX_NUMS_STR="0123456789ABCDEF";

    /**
     * 将指定byte数组转换成16进制字符串
     * @param b
     * @return
     */
    public static String encode(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    public static byte[] decode(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }



}
