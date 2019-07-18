package cn.bucheng.binlog.index;

import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/15 15:12
 * @description：
 * @modified By：
 * @version:
 */
public class DbLoader {

    static String SQL =" select table_schema,table_name,column_name,ordinal_position from information_schema.columns where table_schema =? and table_name=?";


}
