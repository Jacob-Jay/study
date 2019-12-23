package com.jq2.base.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 11:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable{
    private static final long serialVersionUID = 7452009342992953929L;
    private String requestId;
    private Throwable error;
    private Object result;
}
