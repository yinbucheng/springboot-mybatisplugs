package cn.bucheng.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：yinchong
 * @create ：2019/7/12 13:42
 * @description：
 * @modified By：
 * @version:
 */
public class ZookeeperTest {

    //连接地址
    private static final String ADDRESS = "127.0.0.1:2181";
    //session会话
    private static final Integer SESSION_TIMEOUT = 10000;
    //信号量,阻塞程序执行,用户必须等待zookeeper连接成功,发送成功信号
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private ZooKeeper zk;

    @Before
    public void createConnection() {
        createConnection(ADDRESS, SESSION_TIMEOUT);
    }

    /**
     * 创建连接
     *
     * @param address
     * @param sessionTimeout
     */
    public void createConnection(String address, Integer sessionTimeout) {
        try {
            zk = new ZooKeeper(address, sessionTimeout, (watchedEvent -> {
                String path = watchedEvent.getPath();
                Watcher.Event.KeeperState state = watchedEvent.getState();
                if (Watcher.Event.KeeperState.SyncConnected == state) {
                    if (watchedEvent.getType() == Watcher.Event.EventType.NodeCreated) {
                        System.out.println("=====create:" + path);
                    } else if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                        System.out.println("======delete:" + path);
                    } else if (watchedEvent.getType() == Watcher.Event.EventType.NodeDataChanged) {
                        System.out.println("======date change:" + path);
                    }
                }
            }));
            System.out.println("###zookeeper启动连接服务器###");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCreateNode() {
        recursionCreateNode("/mst-tx-job/instance", "192.168.11.90 \n192.168.11.32 \n192.169.34.32");
        createTemplateNode("/mst-tx-job/leader", "192.168.11.90");
//        registerEvent();
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean createTemplateNode(String path, String data) {
        try {
            zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void registerEvent() {
        Watcher tempWatch = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                String tempPath = watchedEvent.getPath();
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                    System.out.println("==================" + tempPath);
//                        zk.register(tempWatch);
                }
            }
        };
        zk.register(tempWatch);
    }

    /**
     * 创建持久化节点
     *
     * @param path
     * @param data
     */
    public boolean createNode(String path, String data) {
        try {
            zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void recursionCreateNode(String path, String data) {
        if (path.lastIndexOf("/") > 0) {
            String currentPath = path.substring(0, path.lastIndexOf("/"));
            recursionCreateNode(currentPath, data);
        }
        Stat stat = exists(path, true);
        if (stat == null) {
            System.out.println("create node ");
            createNode(path, data);
        }
    }

    /**
     * 修改持久化节点
     *
     * @param path
     * @param data
     */
    public boolean updateNode(String path, String data) {
        try {
            this.exists(path, true);
            //阻塞，当等于0的时候释放
            COUNT_DOWN_LATCH.await();
            //zk的数据版本是从0开始计数的。如果客户端传入的是-1，则表示zk服务器需要基于最新的数据进行更新。如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zk.setData(path, data.getBytes(), -1);
            System.out.println("###修改节点信息path:" + path + " data:" + data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除持久化节点
     *
     * @param path
     */
    public boolean deleteNode(String path) {
        try {
            this.exists(path, true);
            //阻塞，当等于0的时候释放
            COUNT_DOWN_LATCH.await();
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zk.delete(path, -1);
            System.out.println("###删除节点信息path:" + path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断指定节点是否存在
     *
     * @param path
     * @param needWatch
     * @return
     */
    public Stat exists(String path, boolean needWatch) {
        try {
            return this.zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @After
    public void close() {
        try {
            if (zk != null) {
                System.out.println("###zookeeper服务已关闭");
                zk.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
