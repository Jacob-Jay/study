package com.jq2.rpc.r1;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 10:39
 */
public interface Constant {
    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
