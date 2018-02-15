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
    @ManyToOne
    private Post postId;
    @ManyToOne
    private  User userId;
    @Column(name = "status_like")
    private LikeStatus likeStatus;
}
