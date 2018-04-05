package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column
    @NotEmpty(message = "name can not be empty")
    private String name;
    @Column
    private String surname;
    @Column(unique = true)
    @Email(message = "please input valid email")
    @NotEmpty(message = "email can not be empty")
    private String email;
    @Column
    @NotEmpty(message = "password can not be empty")
    private String password;
    @Column
    private String picture;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(name = "status_online")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_active")
    private ActiveStatus activeStatus;
    @Column
    boolean verify;
    @Column
    int age;
    @Column
    String city;
}
