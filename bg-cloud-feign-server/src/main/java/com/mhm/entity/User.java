package com.mhm.entity;

import lombok.Builder;
import lombok.ToString;

/**
 * @author Mhm
 * @date 2020-4-15 22:02
 */
@ToString
@Builder
public class User {
    private String id;
    private String name;
    private String address;

    public static void main(String[] args) {
        UserBuilder user = User.builder().id("1").name("admin").address("localhost");
        System.out.println(user.id);
    }
}
