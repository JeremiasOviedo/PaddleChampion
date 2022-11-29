package com.jeremias.paddlechampion.repository;

import com.jeremias.paddlechampion.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);

  UserEntity findByUserId(Long id);

  Page<UserEntity> findAll(Pageable pageable);
}
