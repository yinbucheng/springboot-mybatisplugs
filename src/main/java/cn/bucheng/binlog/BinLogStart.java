package cn.bucheng.binlog;

import cn.bucheng.binlog.dto.Database;
import cn.bucheng.binlog.dto.TableMetaData;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/15 15:24
 * @description：
 * @modified By：
 * @version:
 */
@Component
@Slf4j
public class BinLogStart {

    private Database database = new Database();

    @PostConstruct
    public void init()throws Exception {
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "introcks1234");
//        client.setBinlogFilename();
//        client.setBinlogPosition();
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof TableMapEventData) {
                log.info("table map event......");
                //这里会获取到库名和表名
                TableMapEventData tableData = (TableMapEventData) data;
                String tableName = tableData.getTable();
                String dbName = tableData.getDatabase();
                long tableId = tableData.getTableId();
                TableMetaData temp = database.getTableData(tableId);
                if (temp == null) {
                    temp = new TableMetaData();
                    temp.setDbName(dbName);
                    temp.setTableName(tableName);
                    database.addTableMetaData(tableId, temp);
                }
            } else if (data instanceof UpdateRowsEventData) {
                log.info("update event.........");
                UpdateRowsEventData updateData = (UpdateRowsEventData) data;
                List<Map.Entry<Serializable[], Serializable[]>> rows = updateData.getRows();
                long tableId = updateData.getTableId();
                TableMetaData tableData = database.getTableData(tableId);
                tableData.setType(1);

            } else if (data instanceof DeleteRowsEventData) {
                log.info("delete event........");

            } else if (data instanceof WriteRowsEventData) {
                log.info("insert event........");

            }
        });

        client.connect();
    }

}
