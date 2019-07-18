package cn.bucheng.binlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/15 15:15
 * @description：
 * @modified By：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableMetaData {
    private List<Map<Integer, String>> columnMetaData;
    private String tableName;
    private String dbName;
    private List<Map<String, String>> data;
    private Integer type;
}
