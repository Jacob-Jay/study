package com.jq.nettyt.v005customprotocol.pojo;

import lombok.Data;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 10:02
 */
@Data
public final class NettyMessage {
    private Header header;
    private Object body;


}
