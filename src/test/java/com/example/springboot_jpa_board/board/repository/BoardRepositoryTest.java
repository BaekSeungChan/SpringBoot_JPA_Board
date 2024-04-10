package com.example.springboot_jpa_board.board.repository;

import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.member.entity.Gender;
import com.example.springboot_jpa_board.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
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


    // log 테스트코드 (전통적인 방식은 아님)

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test1(){
        Assertions.assertNotNull(boardRepository);

        log.info(boardRepository.getClass().getName());
    }


    @Test
    public void test2(){
        Board board = Board.builder()
                .title("gogogo")
                .content("hihi")
                .writer("쵸파")
                .build();


        Board result = boardRepository.save(board);

        log.info(String.valueOf(result));
    }


    @Test
    public void test3(){
        Long id = 1L;
        Optional<Board> result = boardRepository.findById(id);

        Board board = result.orElseThrow();

        log.info(String.valueOf(board));
    }

    @Test
    public void test4(){
        Long id = 1L;
        Optional<Board> result = boardRepository.findById(id);

        Board board = result.orElseThrow();

        board.changeTitle("hihi");
        board.changeTitle("good");

        boardRepository.save(board);
    }


    @Test
    public void test5(){
        for( int i = 0; i < 100; i++){
            Board board = Board.builder()
                    .title("gogogo" + i)
                    .content("hihi" + i)
                    .writer("쵸파" + i)
                    .build();

            Board result = boardRepository.save(board);
            log.info(String.valueOf(result));

        }
    }



    @Test
    public void test6(){
        // 페이지 번호는 0 부터

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info(String.valueOf(result.getTotalElements()));
        log.info(result.getContent().toString());

    }

    @Test
    public void testSearch1(){
        boardRepository.search1();
    }


}