package cn.bucheng.discover;

import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

/**
 * @author ：yinchong
 * @create ：2019/7/12 17:05
 * @description：
 * @modified By：
 * @version:
 */
public class ZookeeperDiscoverServer implements ServiceRegistry<ZookeeperRegistration> {

    @Override
    public void register(ZookeeperRegistration registration) {

    }

    @Override
    public void deregister(ZookeeperRegistration registration) {

    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(ZookeeperRegistration registration, String status) {

    }

    @Override
    public <T> T getStatus(ZookeeperRegistration registration) {
        return null;
    }
}
