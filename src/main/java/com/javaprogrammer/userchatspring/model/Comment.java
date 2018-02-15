package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_post")
public class Comment {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @ManyToOne
    private User userId;
    @ManyToOne
    private Post postId;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "file_upload")
    private String file;
    @Column(name = "picture_url")
    private String picture;
}
