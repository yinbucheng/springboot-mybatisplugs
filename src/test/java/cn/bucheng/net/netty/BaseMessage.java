package cn.bucheng.net.netty;

import java.io.Serializable;

/**
 * @author ：yinchong
 * @create ：2019/7/8 14:08
 * @description：
 * @modified By：
 * @version:
 */
public class BaseMessage implements Serializable {
    private String tital;
    private byte[] body;

    public String getTital() {
        return tital;
    }

    public void setTital(String tital) {
        this.tital = tital;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
