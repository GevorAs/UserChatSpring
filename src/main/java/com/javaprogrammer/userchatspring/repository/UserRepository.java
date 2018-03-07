package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User getByEmailAndPassword(String email,String password);
    boolean existsByEmail(String email);
    @Query(value = "select user from User user where (name like %:name%  or surname like %:surname%) or (name like %:surname% or surname like %:name%)")
    List<User> customFindUsersbyNameOrSurname(@Param("name") String name,@Param("surname") String surname);
    List<User> findAllByActiveStatus(ActiveStatus status);
    User findUserByEmail(String email);


}
