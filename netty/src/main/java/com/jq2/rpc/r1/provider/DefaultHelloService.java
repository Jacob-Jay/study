package com.jq2.rpc.r1.provider;

import com.jq2.annotation.RpcService;
import com.jq2.base.service.HelloService;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 9:49
 */
@RpcService(HelloService.class)
public class DefaultHelloService implements HelloService {
    @Override
    public String hello(String name) {
        return "hello     "+name;
    }
}
