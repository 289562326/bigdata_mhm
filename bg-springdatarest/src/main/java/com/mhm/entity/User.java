package com.mhm.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by MaHuiming on 2019/6/12.
 */
@Data
@Entity
@Table(name = "t_auth_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;            // 用户id
    private String user_name;    // 用户名称
    private String password;    // 用户密码
    private Date create_time;    // 账户创建时间
    private Date updatetime;    // 最后更新时间
}
