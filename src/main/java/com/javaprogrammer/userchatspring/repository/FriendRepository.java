package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Integer> {

    @Query(value = "select friend from Friend friend where user_id = :id or  friend_id = :id")
    List<Friend> serchAllFriends(@Param(value = "id") int id);
        Friend findByFriendIdAndUserId(int friendId,int userId);
        Friend findByUserIdAndFriendId(int userId,int friendId);
        @Query(value = "delete from friend where (friend_id=:friendId and user_id=:userId) or (friend_id=:userId and user_id=:frienId)")
        void deleteByFriechchfghfndIdAndUserId(@Param(value = "friendId") int friendId,@Param(value = "userId") int userId);

}
