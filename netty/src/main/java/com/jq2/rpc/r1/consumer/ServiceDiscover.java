package com.jq2.rpc.r1.consumer;

import com.jq2.rpc.r1.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 13:51
 */
public class ServiceDiscover implements InitializingBean{
    private final Logger logger = LoggerFactory.getLogger(ServiceDiscover.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private volatile List<String > list = new ArrayList<>();

    private String address;
    private ZooKeeper zk;

    public ServiceDiscover(String address) {
        this.address = address;
    }

    private void connectZk()  {
        if (StringUtils.isEmpty(address)) {
            logger.error("zk adreess can not be null");
            throw new RuntimeException("");
        }
        try {
            zk = new ZooKeeper(address, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException e) {
            logger.error("zk adreess connect failed");
            throw new RuntimeException("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ZooKeeper getZk() {
        if (zk == null) {
            synchronized (this) {
                if (zk == null) {
                     connectZk();
                }
            }
        }
        return zk;
    }

    public void initProvider() {
        try {
            List<String> children = getZk().getChildren(Constant.ZK_REGISTRY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            });
            List<String > list = new ArrayList<>();
            for (String child : children) {
                byte[] data = zk.getData(Constant.ZK_REGISTRY_PATH + "/" + child, false, null);
                list.add(new String(data));
            }
            this.list = list;

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String discover() {
        String data = null;
        int size = list.size();
        if (size > 0) {
            if (size == 1) {
                data = list.get(0);
                logger.debug("using only data: {}", data);
            } else {
                data = list.get(ThreadLocalRandom.current().nextInt(size));
                logger.debug("using random data: {}", data);
            }
        }
        return data;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initProvider();
    }
}
