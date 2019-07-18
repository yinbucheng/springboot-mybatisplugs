package cn.bucheng.binlog.dto;

import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/15 16:05
 * @description：
 * @modified By：
 * @version:
 */
public class Database {
    private Map<Long, TableMetaData> dataMap = new HashMap<>();

    public TableMetaData getTableData(Long id) {
        return dataMap.get(id);
    }

    public void addTableMetaData(Long id, TableMetaData data) {
        dataMap.put(id, data);
    }
}
