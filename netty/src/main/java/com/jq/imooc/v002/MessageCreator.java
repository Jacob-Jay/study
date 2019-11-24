package com.jq.imooc.v002;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/24 11:02
 */
public class MessageCreator {

    public static final int PROVIDER_PORT = 20000;
    public static final int SEARCH_PORT = 30000;

    private static final String SN_HEADER = "收到暗号：我是(SN)：";
    private static final String PORT_HEADER = "这是暗号：请回电端口：";


    public static String buildProviderMsg(String sn) {
        return SN_HEADER + sn;
    }


    public static String buildSearchMsg(int port) {
        return PORT_HEADER + ""+port;
    }


    public static int parsePort(String s) {
        if (s.startsWith(PORT_HEADER)) {
            return Integer.parseInt(s.substring(PORT_HEADER.length(), s.length()));
        }
        return -1;
    }

    public static String parseSn(String s) {
        if (s.startsWith(SN_HEADER)) {
            return s.substring(SN_HEADER.length(), s.length());
        }
        return null;
    }
}
