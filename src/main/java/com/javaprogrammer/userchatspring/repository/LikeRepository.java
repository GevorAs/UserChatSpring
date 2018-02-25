package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Like;
import com.javaprogrammer.userchatspring.model.LikeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Integer> {

    Like getByUserIdAndImageId(int userId,int imageId);
    Integer countByImageIdAndLikeStatus(int imageId, LikeStatus likestatus);
    Integer countByUserIdAndLikeStatus(int userId,LikeStatus likeStatus);
    Like getByUserIdAndPostId(int userId,int postId);
    Integer countByPostIdAndLikeStatus(int postId,LikeStatus likeStatus);


}
