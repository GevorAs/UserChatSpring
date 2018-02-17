package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "like_posts")
public class Like {

    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column(name = "post_id")
    private int postId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "status_like")
    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;
}
