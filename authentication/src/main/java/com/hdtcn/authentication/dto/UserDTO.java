package com.hdtcn.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    Integer id;
    String mssv;
    String email;
    String name;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", mssv='" + mssv + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
