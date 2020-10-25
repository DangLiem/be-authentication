package com.hdtcn.authentication.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "group_id")
    private Integer group_id;
    @Column(name = "role")
    private String role;
}
