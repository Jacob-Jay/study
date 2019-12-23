package com.jq2.rpc.r1.provider.RpcHandler;

import com.jq.seriliza.pojo.NettyMessage;
import com.jq2.base.dao.RpcRequest;
import com.jq2.base.dao.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 11:31
 */
public class RequestHandler extends SimpleChannelInboundHandler<NettyMessage> {

    private final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Map<String,Object> rpcProviders;

    public RequestHandler(Map<String, Object> rpcProviders) {
        this.rpcProviders = rpcProviders;
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage nettyMessage) throws Exception {
        RpcRequest request = (RpcRequest) nettyMessage.getBody();
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object result = handle(request);
            response.setResult(result);
        } catch (Throwable t) {
            response.setError(t);
        }
        nettyMessage.setBody(response);
        ctx.channel().writeAndFlush(nettyMessage);
    }

    private Object handle(RpcRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = rpcProviders.get(className);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        /*Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);*/

        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("server caught exception", cause);
        ctx.close();
    }
}
