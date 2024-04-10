package com.example.springboot_jpa_board.board.repository.search;

import com.example.springboot_jpa_board.board.entity.Board;
import org.springframework.data.domain.Page;

public interface BoardSearch {

    Page<Board> search1();
}
