package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.board.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testGet(){

        Long id = 50L;
        
        BoardDTO boardDTO = boardService.get(id);

        log.info(String.valueOf(boardDTO));
    }

}