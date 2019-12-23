package com.jq2.rpc.r1.provider;

import com.jq2.base.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 10:17
 */
@Configuration
@PropertySource("classpath:/rpc/provider.properties")
@ComponentScan("com.jq2.rpc.r1.provider")
public class Config {
    @Autowired
    private Environment environment;

    @Bean
    public HelloService helloService() {
        return new DefaultHelloService();
    }

    @Bean
    public ServiceRegistry serviceRegistry() {
        ServiceRegistry registry = new ServiceRegistry(environment.getProperty("zookeeper.address"));
        return registry;
    }

    @Bean
    public RpcService rpcService() {
        RpcService rpcService = new RpcService(serviceRegistry(),environment.getProperty("init.address"));
        return rpcService;
    }

}
