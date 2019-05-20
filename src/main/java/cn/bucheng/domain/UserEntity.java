package cn.bucheng.domain;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @ClassName UserEntity
 * @Author buchengyin
 * @Date 2019/5/20 9:03
 **/
@Alias("User")
@TableName("t_user")
public class UserEntity implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField(value = "name")
    private String name;
    private Integer age;
    private String gender;
    @TableField(exist = false)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
