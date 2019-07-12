package cn.bucheng.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author ：yinchong
 * @create ：2019/7/12 14:59
 * @description：
 * @modified By：
 * @version:
 */
public class PathEventWatch implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        String path = watchedEvent.getPath();
        Event.EventType type = watchedEvent.getType();
        if(type== Event.EventType.NodeDeleted){
            System.out.println("Delete:"+path);
        }else if(type== Event.EventType.NodeCreated){
            System.out.println("Create:"+path);
        }

    }
}
