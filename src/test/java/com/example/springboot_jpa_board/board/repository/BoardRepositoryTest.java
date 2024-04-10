package com.example.springboot_jpa_board.board.repository;

import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.member.entity.Gender;
import com.example.springboot_jpa_board.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BoardRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    public void BoardInsert(){
        Member member = Member.builder()
                .userId("chan")
                .userPassword("1234")
                .userName("백승찬")
                .address("경기도")
                .phone("010")
                .gender(Gender.MEN)
                .build();

        em.persist(member);

        Board board = Board.builder()
                .title("즐거운 코딩")
                .content("오늘 하루도 힘차게")
                .writer("차니")
                .member(member)
                .build();

        em.persist(board);

        em.flush();
        em.clear();

        List<Board> result = em.createQuery("select b from Board b", Board.class)
                .getResultList();

        for(Board board1 : result){
            System.out.println("board " + board1);
            System.out.println("board.member" + board1.getMember());
        }

    }

    @Test
    @Transactional
    public void foreignkeySelect(){

        Member member = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", 1L)
                .getSingleResult();


        System.out.println("memberTest : " + member.getBoardList());
    }

}