package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl {

    private MemberRepository memberRepository;

}
