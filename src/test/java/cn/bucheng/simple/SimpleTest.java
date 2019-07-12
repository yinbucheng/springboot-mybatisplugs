package cn.bucheng.simple;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：yinchong
 * @create ：2019/7/11 9:41
 * @description：
 * @modified By：
 * @version:
 */
public class SimpleTest {

    @Test
    public void test(){
        String content ="name=yinchong&age=10";
        Map<String, String> stringStringMap = queryToMap(content);
        System.out.println(stringStringMap);
    }


    private static Map<String, String> queryToMap(String query) {
        String[] temps = query.split("&");
        if (null == temps) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String temp : temps) {
            String[] split = temp.split("=");
            result.put(split[0], split[1]);
        }
        return result;
    }

    @Test
    public void testLocalHostName(){
        try {
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println(ia.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
