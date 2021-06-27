package com.vicky.demo_s.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @className Customer
 * @desc 用来测试JPA功能而创建的，测试，加点描述
 * @author Vic
 * @version 1.0
 * @date 2021/5/23 4:38 下午
 **/
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

}
