package cn.bucheng.binlog;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ：yinchong
 * @create ：2019/7/15 8:56
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class BinlogTest {

    @Test
    public void binlogTest() throws Exception {
//        TemplateHolder.loadMetaData();
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "introcks1234");
//        client.setBinlogFilename("mysql-bin.000001");
//        client.setBinlogPosition(0);
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof TableMapEventData) {
                log.info("table map event......");
                log.info(data.toString());
            } else if (data instanceof UpdateRowsEventData) {
                log.info("update event.........");
                log.info(data.toString());
                UpdateRowsEventData eventData = (UpdateRowsEventData)data;
                byte[] bytes = eventData.getIncludedColumns().toByteArray();
                log.info("test");
            } else if (data instanceof DeleteRowsEventData) {
                log.info("delete event........");
                log.info(data.toString());
            } else if (data instanceof WriteRowsEventData) {
                WriteRowsEventData eventData = (WriteRowsEventData) data;
                log.debug("insert event........");
                log.debug(data.toString());
                log.info(Arrays.asList(eventData.getIncludedColumns().toLongArray()) + "");
                log.info(eventData.getRows() + "");
            }
        });

        client.connect();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testLoadBinLog() throws Exception {
        log.info("begin test..........");
        TemplateHolder.loadMetaData();
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "introcks1234");
        client.registerEventListener(new BinlogListener());
        client.connect();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
