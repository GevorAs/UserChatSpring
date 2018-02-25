package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Like_all")
public class Like {

    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column(name = "post_id")
    private int postId;
    @Column(name = "image_id")
    private int imageId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "status_like")
    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;
}
