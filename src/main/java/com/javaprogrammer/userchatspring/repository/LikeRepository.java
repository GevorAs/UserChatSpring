package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Integer> {

    Like getByUserAndImage(User user, Image image);
    Integer countByImageAndLikeStatus(Image image, LikeStatus likestatus);
    Integer countByUserAndLikeStatus(User user,LikeStatus likeStatus);
    Like getByUserAndPost(User user,Post post);
    Integer countByPostAndLikeStatus(Post post,LikeStatus likeStatus);


}
