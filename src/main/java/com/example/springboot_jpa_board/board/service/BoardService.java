package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.board.dto.BoardDTO;
import com.example.springboot_jpa_board.board.entity.Board;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface BoardService {

    BoardDTO get(Long id);

    Long register(BoardDTO boardDTO);

    void modify(BoardDTO boardDTO);

    void remove(Long id);


    default BoardDTO entityToDTO(Board board){
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .member(board.getMember())
                .build();
    }

    default Board dtoToEntity(BoardDTO boardDTO){
        return Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .member(boardDTO.getMember())
                .build();
    }

}
