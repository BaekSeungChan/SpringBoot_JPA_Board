package com.example.springboot_jpa_board.board.repository;

import com.example.springboot_jpa_board.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}