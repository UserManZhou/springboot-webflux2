package com.spring.boot.webflux2.entity;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private BigInteger money;

}
