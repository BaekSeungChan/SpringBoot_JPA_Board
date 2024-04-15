package com.example.springboot_jpa_board.board.repository.search;

import com.example.springboot_jpa_board.board.dto.PageRequestDTO;
import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.board.entity.QBoard;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(PageRequestDTO pageRequestDTO) {

        log.info("search1................");

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

//        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").descending());
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1 ,
                pageRequestDTO.getSize(),
                Sort.by("id").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch(); // 목록 데이터

        long  total = query.fetchCount();


        return new PageImpl<>(list, pageable, total);
    }
}
