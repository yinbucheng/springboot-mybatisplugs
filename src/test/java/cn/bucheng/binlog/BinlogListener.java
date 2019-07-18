package cn.bucheng.binlog;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/17 15:37
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class BinlogListener implements BinaryLogClient.EventListener {

    @Override
    public void onEvent(Event event) {
        EventData data = event.getData();
        if (data instanceof TableMapEventData) {
            TableMapEventData metaData = (TableMapEventData) data;
            String tableName = metaData.getTable();
            String dbName = metaData.getDatabase();
            long tableId = metaData.getTableId();
            TemplateHolder.addMapping(tableId, dbName, tableName);
        } else if (data instanceof UpdateRowsEventData) {
            List<Map<String, String>> result = new LinkedList<>();
            UpdateRowsEventData eventData = (UpdateRowsEventData) data;
            long tableId = eventData.getTableId();
            String key = TemplateHolder.getMapping(tableId);
            List<Map.Entry<Serializable[], Serializable[]>> rows = eventData.getRows();
            for (Map.Entry<Serializable[], Serializable[]> row : rows) {
                Map<String, String> temp = new LinkedHashMap<>();
                Serializable[] values = row.getValue();
                int size = values.length;
                for (int i = 0; i < size; i++) {
                    String value = values[i] + "";
                    String columnName = TemplateHolder.getColumnName(key, i);
                    temp.put(columnName, value);
                }
                result.add(temp);
            }
            log.info("update "+result.toString());
        } else if (data instanceof WriteRowsEventData) {
            List<Map<String, String>> result = new LinkedList<>();
            WriteRowsEventData eventData = (WriteRowsEventData) data;
            long tableId = eventData.getTableId();
            String key = TemplateHolder.getMapping(tableId);
            List<Serializable[]> rows = eventData.getRows();
            for (Serializable[] row : rows) {
                int size = row.length;
                Map<String, String> temp = new LinkedHashMap<>();
                for (int i = 0; i < size; i++) {
                    String value = row[i] + "";
                    String columnName = TemplateHolder.getColumnName(key, i);
                    temp.put(columnName, value);
                }
                result.add(temp);
            }
            log.info("insert "+result.toString());
        } else if (data instanceof DeleteRowsEventData) {
            DeleteRowsEventData eventData = (DeleteRowsEventData) data;
            List<Map<String, String>> result = new LinkedList<>();
            long tableId = eventData.getTableId();
            String key = TemplateHolder.getMapping(tableId);
            List<Serializable[]> rows = eventData.getRows();
            for (Serializable[] row : rows) {
                int size = row.length;
                Map<String, String> temp = new LinkedHashMap<>();
                for (int i = 0; i < size; i++) {
                    String value = row[i] + "";
                    String columnName = TemplateHolder.getColumnName(key, i);
                    temp.put(columnName, value);
                }
                result.add(temp);
            }
            log.info("delete "+result.toString());
        }
    }
}
