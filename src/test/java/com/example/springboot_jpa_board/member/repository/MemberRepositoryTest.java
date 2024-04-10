package com.example.springboot_jpa_board.member.repository;

import com.example.springboot_jpa_board.member.entity.Gender;
import com.example.springboot_jpa_board.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberInsert(){

        IntStream.rangeClosed(0, 10).forEach(i -> {

            Member member = Member.builder()
                    .userId("qortmdcks95" + i)
                    .userPassword("1234" + i)
                    .userName("찬이야" + i)
                    .address("경기도" + i)
                    .phone("010")
                    .gender(Gender.WOMEN)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void memberFindAll(){
        List<Member> members = memberRepository.findAll();

        for(Member member : members){
            System.out.println("MemberfindAll : " + member);
        }
    }

}