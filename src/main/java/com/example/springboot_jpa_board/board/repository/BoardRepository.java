package com.example.springboot_jpa_board.board.repository;

import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.board.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}
