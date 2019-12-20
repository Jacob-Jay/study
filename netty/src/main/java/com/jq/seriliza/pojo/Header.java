package com.jq.seriliza.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 10:02
 */
@Data
public final class Header {
    private int ercCode = 0xabef0101;
    private int length;
    private long sessionId;
    private byte type;
    private byte priority;
    private Map<String,Object>attachment = new HashMap<>();
}
