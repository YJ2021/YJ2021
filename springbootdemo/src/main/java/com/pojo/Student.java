package com.pojo;

import com.user.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
/*
*@Component只有这个组件是容器的组件，才能提供@ConfigurationProperties(prefix = "student")这个功能
* @ConfigurationProperties告诉spring将本类中的属性与配置文件中的相关配置绑定
* (prefix = "student")配置文件中哪个的下面进行意义映射
* @Validated JSR303数据校验 需要导入依赖 spring-boot-starter-validation
* */
@Component
@ConfigurationProperties(prefix = "student")
@Validated
public class Student implements Serializable {
    private  String name;
    @Min(30)//最小值
    private int age;
    private String address;
    private Map map;
    private List<?> list;
    private User user;
    public Student() {
    }

    public Student(String name, int age, String address, Map map, List<?> list, User user) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.map = map;
        this.list = list;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", map=" + map +
                ", list=" + list +
                ", user=" + user +
                '}';
    }
}
