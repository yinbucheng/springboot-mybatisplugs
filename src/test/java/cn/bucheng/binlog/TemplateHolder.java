package cn.bucheng.binlog;

import cn.bucheng.binlog.dto.TableStruct;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/17 14:48
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class TemplateHolder {
    private static Map<String, TableStruct> cache = new HashMap<>();
    private static Map<Long, String> mappingTables = new HashMap<>();

    public static void addMapping(long tableId, String dbName, String tableName) {
        mappingTables.put(tableId, createKey(dbName, tableName));
    }

    public static String getMapping(long tableId) {
        return mappingTables.get(tableId);
    }

    private static Connection connection;

    private static String SQL_SCHEMA = "select table_schema, table_name, " +
            "column_name, ordinal_position from information_schema.columns ";
//            "where table_schema = ? and table_name = ?";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yinchong?serverTimezone=GMT%2B8", "root", "introcks1234");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    public static void loadMetaData() throws Exception {
        PreparedStatement ps = connection.prepareStatement(SQL_SCHEMA);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                String dbName = rs.getString("table_schema");
                String tableName = rs.getString("table_name");
                String columnName = rs.getString("column_name");
                Integer index = rs.getInt("ordinal_position");
                TableStruct tableBO = cache.get(createKey(dbName, tableName));
                if (tableBO == null) {
                    tableBO = new TableStruct();
                    tableBO.setTableName(tableName);
                    tableBO.setDbName(dbName);
                    cache.put(createKey(dbName, tableName), tableBO);
                }

                tableBO.addIndexColumnRelation(index - 1, columnName);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        ps.close();
    }

    public static String createKey(String dbName, String tableName) {
        return dbName + ":" + tableName;
    }

    public static String getColumnName(String dbName, String tableName, int position) {
        return getColumnName(createKey(dbName, tableName), position);
    }

    public static String getColumnName(String key, int position) {
        TableStruct tableBO = cache.get(key);
        if (tableBO == null) {
            return null;
        }

        return tableBO.getColumnName(position);
    }
}
