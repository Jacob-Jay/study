package com.jq.encryption;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-18 11:33
 */
public class CharEncy {
    public static String encrypt(String sourceString, String password) {
        char[] p = password.toCharArray(); // 字符串转字符数组
        int n = p.length; // 密码长度

        char[] c = sourceString.toCharArray();
        int m = c.length; // 字符串长度

        for (int k = 0; k < m; k++) {
            int mima = c[k] + p[k / n]; // 加密
            c[k] = (char) mima;
        }
        return new String(c);
    }

    public static String decrypt(String sourceString, String password) {
        char[] p = password.toCharArray(); // 字符串转字符数组
        int n = p.length; // 密码长度
        char[] c = sourceString.toCharArray();
        int m = c.length; // 字符串长度
        for (int k = 0; k < m; k++) {
            int mima = c[k] - p[k / n]; // 解密
            c[k] = (char) mima;
        }
        return new String(c);
    }

    public static void main(String arg[]) {
        String wen = "这是一段明文";
        String pass = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        System.out.println(encrypt(wen, pass));
        System.out.println(decrypt(encrypt(wen, pass), pass));
    }
}
