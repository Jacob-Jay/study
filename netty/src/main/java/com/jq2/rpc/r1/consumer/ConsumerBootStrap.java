package com.jq2.rpc.r1.consumer;

import com.jq2.base.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 14:54
 */
public class ConsumerBootStrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println(result);
//        Object did = rpcProxy.did();
//        System.out.println(did);
    }
}
