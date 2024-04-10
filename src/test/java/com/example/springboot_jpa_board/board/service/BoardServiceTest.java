package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.board.dto.BoardDTO;
import com.example.springboot_jpa_board.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private BoardService boardService;

    @Test
    public void testGet(){

        Long id = 50L;

        BoardDTO boardDTO = boardService.get(id);

        log.info(String.valueOf(boardDTO));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testRegister(){

        Member member = Member.builder()
                .build();

        em.persist(member);


        BoardDTO boardDTO = BoardDTO.builder()
                .title("good.....")
                .content("contet......")
                .writer("chan")
                .member(member)
                .build();

        Long update = boardService.register(boardDTO);

        log.info("success " + String.valueOf(update));

    }


    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .id(105L)
                .title("^^^^^^^")
                .content("ㅠㅜㅠㅠㅠㅠ")
                .build();

        boardService.modify(boardDTO);

    }

    @Test
    public void testRemove(){

        boardService.remove(103L);
    }



}