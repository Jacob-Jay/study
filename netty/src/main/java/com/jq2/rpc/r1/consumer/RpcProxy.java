package com.jq2.rpc.r1.consumer;

import com.jq2.base.dao.RpcRequest;
import com.jq2.base.dao.RpcResponse;
import com.jq2.base.service.HelloService;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 14:13
 */
public class RpcProxy {
    private String serverAddress;
    private ServiceDiscover serviceDiscovery;

    public RpcProxy(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcProxy(ServiceDiscover serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public Object did() {
        try {
            Method hello = HelloService.class.getMethod("hello",String.class);
            RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
            request.setRequestId(UUID.randomUUID().toString());
            request.setClassName(hello.getDeclaringClass().getName());
            request.setMethodName(hello.getName());
            request.setParameterTypes(hello.getParameterTypes());
            request.setParameters(new Object[]{"jq"});

            if (serviceDiscovery != null) {
                serverAddress = serviceDiscovery.discover(); // 发现服务
            }

            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);

            RpcClient client = new RpcClient(host, port); // 初始化 RPC 客户端
            RpcResponse response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

            if (response.getError()!=null) {
                throw response.getError();
            } else {
                return response.getResult();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "";
    }


    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);

                        if (serviceDiscovery != null) {
                            serverAddress = serviceDiscovery.discover(); // 发现服务
                        }

                        String[] array = serverAddress.split(":");
                        String host = array[0];
                        int port = Integer.parseInt(array[1]);

                        RpcClient client = new RpcClient(host, port); // 初始化 RPC 客户端
                        RpcResponse response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

                        if (response.getError()!=null) {
                            throw response.getError();
                        } else {
                            return response.getResult();
                        }
                    }
                }
        );
    }
}
