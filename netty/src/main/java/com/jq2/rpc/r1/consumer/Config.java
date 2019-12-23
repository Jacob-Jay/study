package com.jq2.rpc.r1.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 13:48
 */
@Configuration
@ComponentScan("com.jq2.rpc.r1.consumer")
@PropertySource(value = "classpath:/rpc/comsumer.properties")
public class Config {
    @Autowired
    private Environment environment;

    @Bean
    public ServiceDiscover serviceDiscover() {
        ServiceDiscover discover = new ServiceDiscover(environment.getProperty("zookeeper.address"));
        return discover;
    }

    @Bean
    public RpcProxy rpcProxy() {
        RpcProxy rpcProxy = new RpcProxy(serviceDiscover());
        return rpcProxy;
    }
}
