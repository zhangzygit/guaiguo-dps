package top.guaiguo.springdps.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangzy
 * @version 1.0.0
 * @createTime 2020/12/19
 * @Description
 */
@Data
public class Student {
    private long id;
    private String name;
    private int age;
    private Date birthday;
}
