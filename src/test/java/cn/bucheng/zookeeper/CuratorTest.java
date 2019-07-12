package cn.bucheng.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @author ：yinchong
 * @create ：2019/7/12 15:30
 * @description：
 * @modified By：
 * @version:
 */
public class CuratorTest {

    @Test
    public void test() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(10000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        //添加事件监听
        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("监听事件触发，event内容为：" + event);
            }
        };
        client.getCuratorListenable().addListener(listener);

         client.create().forPath("/yinbucheng");
        Stat temp= client.checkExists().forPath("/yinbucheng");
        System.out.println("==============="+temp);
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/yinbucheng/test/leader", "192.168.11.178".getBytes());
//        byte[] content = client.getData().forPath("/yinbucheng/test/leader");
//        System.out.println(new String(content));
//        client.setData().forPath("/yinbucheng/test/leader", "192.168.78.22".getBytes());
//        content = client.getData().forPath("/yinbucheng/test/leader");
//        System.out.println(new String(content));
//        client.delete().deletingChildrenIfNeeded().forPath("/yinbucheng");

    }
}
