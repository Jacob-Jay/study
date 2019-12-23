package com.jq2.rpc.r1.consumer;

import com.jq.nettyt.v005customprotocol.enDecode.OwnDecoder;
import com.jq.nettyt.v005customprotocol.enDecode.OwnEncoder;
import com.jq.seriliza.pojo.Header;
import com.jq.seriliza.pojo.NettyMessage;
import com.jq2.base.dao.RpcRequest;
import com.jq2.base.dao.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 14:15
 */
public class RpcClient extends SimpleChannelInboundHandler<NettyMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);

    private String host;
    private int port;

    private RpcResponse response;

    private final Object obj = new Object();

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, NettyMessage nettyMessage) throws Exception {
        this.response = (RpcResponse) nettyMessage.getBody();

        synchronized (obj) {
            obj.notifyAll(); // 收到响应，唤醒线程
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
        ctx.close();
    }

    public RpcResponse send(RpcRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new OwnEncoder()) // 将 RPC 请求进行编码（为了发送请求）
                                    .addLast(new OwnDecoder()) // 将 RPC 响应进行解码（为了处理响应）
                                    .addLast(RpcClient.this); // 使用 RpcClient 发送 RPC 请求
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.connect(host, port).sync();
            NettyMessage nettyMessage = new NettyMessage();
            Header header = new Header();
            nettyMessage.setHeader(header);
            nettyMessage.setBody(request);

            future.channel().writeAndFlush(nettyMessage).sync();

            synchronized (obj) {
                obj.wait(); // 未收到响应，使线程等待
            }

            if (response == null) {
                future.channel().closeFuture().sync();
            }
            return response;
        } finally {
            group.shutdownGracefully();
        }
    }
}
