package com.javaprogrammer.userchatspring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "text")
    private String text;
    @Column(name = "file_upload_post")
    private String file;
    @Column(name = "pic_url_post")
    private String picture;
}
