package cn.bucheng.springboot.annotest.bean;

/**
 * @author ：yinchong
 * @create ：2019/6/26 20:48
 * @description：
 * @modified By：
 * @version:
 */
public class BeanTwo implements Bean {
    @Override
    public void test() {
        System.out.println("BeanTwo");
    }
}
