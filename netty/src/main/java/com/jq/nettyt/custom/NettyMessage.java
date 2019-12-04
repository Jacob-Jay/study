package com.jq.nettyt.custom;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-03 19:16
 */
public class NettyMessage {
    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
