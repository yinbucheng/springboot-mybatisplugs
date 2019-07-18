package cn.bucheng.binlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/15 14:54
 * @description：
 * @modified By：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableStruct {
    private String tableName;
    private String dbName;
    //下标和列名的关系
    private Map<Integer, String> positionName = new HashMap<>();
    //列名和字段名称对应关系
    private Map<String, String> columnFields = new HashMap<>();

    public void addIndexColumnRelation(int position, String columnName) {
        positionName.put(position, columnName);
    }

    public String getColumnName(int position) {
        return positionName.get(position);
    }


    public void addColumnFieldRelation(String column, String field) {
        columnFields.put(column, field);
    }

    public String getFieldName(String columnName) {
        return columnFields.get(columnName);
    }

}
