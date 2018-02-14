package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "pic_url")
    private String picture;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "status")
    private ActiveStatus activeStatus;

}
