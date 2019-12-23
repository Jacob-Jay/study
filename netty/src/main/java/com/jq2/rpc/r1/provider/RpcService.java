package com.jq2.rpc.r1.provider;

import com.jq.nettyt.v005customprotocol.enDecode.OwnDecoder;
import com.jq.nettyt.v005customprotocol.enDecode.OwnEncoder;
import com.jq2.rpc.r1.provider.RpcHandler.RequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 11:10
 */
public class RpcService implements ApplicationContextAware,InitializingBean {

    private ServiceRegistry serviceRegistry;
    private String address;

    public RpcService(ServiceRegistry serviceRegistry, String address) {
        this.serviceRegistry = serviceRegistry;
        this.address = address;
    }

    private final Logger logger = LoggerFactory.getLogger(RpcService.class);

    //  interfaceName  Instantiate
    private Map<String,Object> rpcProviders = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(com.jq2.annotation.RpcService.class);
        if (!CollectionUtils.isEmpty(beansWithAnnotation)) {
            for (Object bean : beansWithAnnotation.values()) {
                String name = bean.getClass().getAnnotation(com.jq2.annotation.RpcService.class).value().getName();
                rpcProviders.put(name, bean);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initNetty();
    }

    public void initNetty() {
        NioEventLoopGroup w = new NioEventLoopGroup();
        NioEventLoopGroup b = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            String[] split = address.split(":");
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            ChannelFuture future = server.group(w, b)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new OwnDecoder());
                            pipeline.addLast(new OwnEncoder());
                            pipeline.addLast(new RequestHandler(rpcProviders));
                        }
                    }).bind(host, port).sync();
            serviceRegistry.registry(address);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("start rpcServer failed...",e);
            throw new RuntimeException("");
        } finally {
            w.shutdownGracefully();
            b.shutdownGracefully();
        }
    }
}
