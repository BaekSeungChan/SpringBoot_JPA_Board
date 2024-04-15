package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.board.dto.BoardDTO;
import com.example.springboot_jpa_board.board.dto.PageRequestDTO;
import com.example.springboot_jpa_board.board.dto.PageResponseDTO;
import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.board.repository.BoardRepository;
import com.example.springboot_jpa_board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = dtoToEntity(boardDTO);

        Board result = boardRepository.save(board);

        return result.getId();
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getId());

        Board board = result.orElseThrow();

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }

    @Override
    public void remove(Long id) {
        boardRepository.deleteById(id);
    }


    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        Page<Board> result = boardRepository.search1(pageRequestDTO);

        List<BoardDTO> dtoList = result.get().map(todo -> entityToDTO(todo)).collect(Collectors.toList());


        PageResponseDTO<BoardDTO> responseDTO = PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total(result.getTotalElements())
                .build();



        return responseDTO;
    }
}
