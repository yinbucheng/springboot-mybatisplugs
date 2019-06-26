package cn.bucheng.springboot.test;

/**
 * @author ：yinchong
 * @create ：2019/6/26 16:18
 * @description：cat
 * @modified By：
 * @version:
 */
public class Cat implements Animal {
    @Override
    public void eat(String param) {
        System.out.println("我爱吃 "+param);
    }

    @Override
    public String say() {
        return "我爱喵喵叫";
    }
}
