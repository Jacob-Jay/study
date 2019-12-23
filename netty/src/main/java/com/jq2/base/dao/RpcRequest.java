package com.jq2.base.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable{
    private static final long serialVersionUID = -2322096893139236500L;
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}
