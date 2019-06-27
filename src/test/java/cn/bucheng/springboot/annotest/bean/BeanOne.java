package cn.bucheng.springboot.annotest.bean;

/**
 * @author ：yinchong
 * @create ：2019/6/26 20:47
 * @description：
 * @modified By：
 * @version:
 */
public class BeanOne implements Bean {
    @Override
    public void test() {
        System.out.println("BeanOne");
    }
}
