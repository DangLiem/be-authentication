package com.hdtcn.authentication.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    @Column(name = "role_id")
    private Integer role_id;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", group_id=" + group_id +
                ", role_id=" + role_id +
                '}';
    }
}
