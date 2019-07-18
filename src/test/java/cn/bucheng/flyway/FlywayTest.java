package cn.bucheng.flyway;

import ch.qos.logback.core.db.dialect.DBUtil;
import org.flywaydb.core.Flyway;
import org.junit.Test;

/**
 * @author ：yinchong
 * @create ：2019/7/16 17:01
 * @description：
 * @modified By：
 * @version:
 */
public class FlywayTest {

    @Test
    public void testLoad() {
//        long startTime = System.currentTimeMillis();
//        String url = "jdbc:mysql://127.0.0.1:3306/ifaas_basicinfo?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true&useSSL=false&serverTimezone=GMT%2B8";
//        String username = "root";
//        String password = "introcks1234";
//        Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
//        flyway.migrate();
//        long endTime = System.currentTimeMillis();
//        System.out.println("use " + ((endTime - startTime) / 1000) + " s to build sql");
    }


    @Test
    public void testLoad2(){
        long startTime = System.currentTimeMillis();
        try {
            DbUtil.initDbAndFlyWay();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            long endTime = System.currentTimeMillis();
            System.out.println(((endTime-startTime)/1000));
        }
    }
}
