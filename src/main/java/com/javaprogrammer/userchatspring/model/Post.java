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
    @ManyToOne
    private User user;
    @Column(name = "text")
    private String text;
    @Column(name = "file_upload_post")
    private String file;
    @Column(name = "pic_url_post")
    private String picture;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;
    @Column(name = "created_date")
    private  String createdDate;
    @Column(name = "like_count")
    private int likeCount;
    @Column(name = "dislike_count")
    private int dislikeCount;

    public Post(User user, String text, String file, String picture, ActiveStatus activeStatus, int likeCount, int dislikeCount) {
        this.user = user;
        this.text = text;
        this.file = file;
        this.picture = picture;
        this.activeStatus = activeStatus;

        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
}
