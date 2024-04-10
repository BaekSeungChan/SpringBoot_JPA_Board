package com.example.springboot_jpa_board.board.dto;

import com.example.springboot_jpa_board.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private Member member;
}
