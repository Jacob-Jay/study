package com.jq2.rpc.r1.provider;

import com.jq2.rpc.r1.Constant;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-23 10:39
 */
public class ServiceRegistry {
    private static Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);

    private   ZooKeeper  zk;
    private String address;
    private CountDownLatch latch = new CountDownLatch(1);

    public ServiceRegistry(String address) {
        this.address = address;
    }

    public void connectZk() {
        if (StringUtils.isEmpty(address)) {
            logger.error("address can not be empty!");
        }
        try {
            zk = new ZooKeeper(address, com.jq2.rpc.r1.Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                   if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch ( Exception e) {
            logger.error("connect to zkServer failed!");
            throw new RuntimeException("");
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

    public void registry(String data) {
        try {
            //data = "8877";
            byte[] bytes = data.getBytes();
            String path = getZk().create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.debug("create zookeeper node ({} => {})", path, data);
        } catch (KeeperException | InterruptedException e) {
            logger.error("", e);
            throw new RuntimeException("");
        }
    }
}
