package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.board.dto.BoardDTO;
import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.board.repository.BoardRepository;
import com.example.springboot_jpa_board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public BoardDTO get(Long id) {

        Optional<Board> result = boardRepository.findById(id);

        Board board = result.orElseThrow();
        return entityToDTO(board);
    }
}
