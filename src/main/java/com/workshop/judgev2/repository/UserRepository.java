package com.workshop.judgev2.repository;

import com.workshop.judgev2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Query("select u.username from User u order by u.username")
    List<String> findAllByUsername();
}
