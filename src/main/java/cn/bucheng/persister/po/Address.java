package cn.bucheng.persister.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ：yinchong
 * @create ：2019/6/26 13:49
 * @description：地址
 * @modified By：
 * @version:
 */
@Document(collection = "address")
public class Address {
    /**
     * 编号.
     */
    @Id
    private String id;
    /**
     * 省.
     */
    @Field
    private String province;
    /**
     * 市.
     */
    @Field
    private String city;
    /**
     * 区.
     */
    @Field
    private String district;
    /**
     * 状态.
     */
    @Field
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", status=" + status +
                '}';
    }
}
