package com.example.projectone.auth;

import com.example.projectone.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<UserEntity, Long> {
//    Optional<ApplicationUser> getApplicationUserByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
