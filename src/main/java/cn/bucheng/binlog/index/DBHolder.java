package cn.bucheng.binlog.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/15 15:18
 * @description：
 * @modified By：
 * @version:
 */
public class DBHolder {
    static String SQL = "elect table_schema,`table_name`,`column_name`,ordinal_position from information_schema.columns where table_schema =? and table_name=?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Map<Integer, String> loadMetaData(String dbName, String tableName) {
        Map<Integer, String> metaData = new HashMap<>();
        jdbcTemplate.query(SQL, new Object[]{dbName, tableName}, (rs) -> {
            int index = rs.getInt("ordinal_position");
            String columnName = rs.getString("column_name");
            metaData.put(index - 1, columnName);
        });
        return metaData;
    }



}
