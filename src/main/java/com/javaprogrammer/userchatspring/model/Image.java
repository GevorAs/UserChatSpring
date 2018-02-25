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
    @ManyToOne
    private User user;
    @Column(name = "pic_url")
    private String picture;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;
    @Column(name = "like_count")
    private int likeCount;
    @Column(name = "dislike_count")
    private int dislikeCount;

}
