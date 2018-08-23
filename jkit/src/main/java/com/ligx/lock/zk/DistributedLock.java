package com.ligx.lock.zk;

import com.ligx.lock.LockException;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/08/23.
 */
public class DistributedLock implements Watcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLock.class);

    private ZooKeeper zk;
    private String root = "/locks";  //根
    private String lockName;         //竞争资源的标志
    private String waitNode;         //等待前一个锁
    private String myNode;          //当前锁
    private CountDownLatch latch;    //计数器
    private CountDownLatch connectedLatch = new CountDownLatch(1); //等待连接成功的锁
    private int sessionTimeout = 30000;


    public DistributedLock(String zkString, String lockName) {
        this.lockName = lockName;
        try {
            zk = new ZooKeeper(zkString, sessionTimeout, this);
            if (ZooKeeper.States.CONNECTING == zk.getState()) {
                try {
                    connectedLatch.await();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
            init();
        } catch (Exception e) {
            LOGGER.error("DistributedLock#contructor, error", e);
            throw new LockException(e);
        }
    }

    private void init() {
        try {
            Stat stat = zk.exists(root, false);
            if (stat == null) {
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            LOGGER.error("DistributedLock#init, error, ", e);
            throw new LockException(e);
        }
    }

    //zookeeper节点的监视器
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedLatch.countDown();
        }
        if (this.latch != null) {
            this.latch.countDown();
        }
    }

    public void lock() {
        try {
            if (!this.tryLock()) {
                waitForLock(waitNode, sessionTimeout, TimeUnit.MILLISECONDS); //等待锁
            }
        } catch (Exception e) {
            LOGGER.error("DistributedLock#lock, error, ", e);
            throw new LockException(e);
        }
    }

    public boolean tryLock(long time, TimeUnit timeUnit) {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(waitNode, time, timeUnit);
        } catch (Exception e) {
            LOGGER.error("DistributedLock#tryLock, time={}", time, e);
        }
        return false;
    }

    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (lockName.contains(splitStr)){
                throw new LockException("lockName can not contains \\u000B");
            }
            // 创建有序临时子节点
            myNode = zk.create(root + "/" + lockName + splitStr, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 取出所有子节点
            List<String> subNodes = zk.getChildren(root, false);
            // 取出所有lockName的子节点
            List<String> lockObjSubNodes = new ArrayList<>();
            for (String subNode : subNodes) {
                String _node = subNode.split(splitStr)[0];
                if (_node.equals(lockName)) {
                    lockObjSubNodes.add(subNode);
                }
            }
            // 对所有lockName的子节点进行排序
            Collections.sort(lockObjSubNodes);
            if (myNode.equals(root + "/" + lockObjSubNodes.get(0))) {
                //如果是最小的节点,则表示取得锁
                return true;
            }
            // 如果不是最小的节点，找到比自己小1的节点
            String subMyNode = myNode.substring(myNode.lastIndexOf("/") + 1);
            waitNode = lockObjSubNodes.get(Collections.binarySearch(lockObjSubNodes, subMyNode) - 1);
        } catch (Exception e) {
            LOGGER.error("DistributedLock#tryLock, error, ", e);
            throw new LockException(e);
        }
        return false;
    }

    private boolean waitForLock(String subNode, long waitTime, TimeUnit timeUnit) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(root + "/" + subNode, true);
        //判断比自己小一个数的节点是否存在,如果不存在则无需等待锁,同时注册监听
        if (stat != null) {
            this.latch = new CountDownLatch(1);
            this.latch.await(waitTime, timeUnit);
            this.latch = null;
        }
        return true;
    }

    public void unlock() {
        try {
            zk.delete(myNode, -1);
            myNode = null;
        } catch (Exception e) {
            LOGGER.error("DistributedLock#unlock, error, ", e);
            throw new LockException(e);
        }
    }
}

