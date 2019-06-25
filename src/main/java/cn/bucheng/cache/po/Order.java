package cn.bucheng.cache.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：yinchong
 * @create ：2019/6/25 19:10
 * @description：order订单
 * @modified By：
 * @version:
 */
public class Order implements Serializable {
    private Long orderId;

    private BigDecimal payment;

    private String paymentType;

    private String postFee;

    private String status;
    private Date createTime;
    private Date updateTime;

    private Date closeTime;
    private String shippingName;

    private String buyerMessage;

    private String buyerNick;
    private String buyerRate;

    private Date expire;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", payment=" + payment +
                ", paymentType='" + paymentType + '\'' +
                ", postFee='" + postFee + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", closeTime=" + closeTime +
                ", shippingName='" + shippingName + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", buyerRate='" + buyerRate + '\'' +
                ", expire=" + expire +
                '}';
    }
}
