package com.example.springboot_jpa_board.board.repository;

import com.example.springboot_jpa_board.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Member, Long> {
}
