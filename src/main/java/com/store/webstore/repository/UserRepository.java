package com.store.webstore.repository;

import com.store.webstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("select u from User u")
    List<User> findAll();

    @Query("select u from User u left join fetch u.roles where u.email = ?1")
    User findByEmail(String email);
}
