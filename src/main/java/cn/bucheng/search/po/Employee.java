package cn.bucheng.search.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "megacorp", type = "employee", shards = 1, replicas = 0, refreshInterval = "-1")
public class Employee implements Serializable {
    @Id
    private String id;
    @Field(type = FieldType.Text, index = false)
    private String firstName;
    @Field(type = FieldType.Text, index = false)
    private String lastName;
    @Field(type = FieldType.Integer)
    private Integer age = 0;
    //配置使用ik中文分词器
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String about;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String remark;
    @Field(type = FieldType.Keyword)
    private String telephone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", about='" + about + '\'' +
                ", remark='" + remark + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
