package com.example.springboot_jpa_board.member.repository;

import com.example.springboot_jpa_board.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
