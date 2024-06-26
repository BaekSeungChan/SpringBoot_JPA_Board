package com.example.springboot_jpa_board.board.entity;

import com.example.springboot_jpa_board.common.BaseEntity;
import com.example.springboot_jpa_board.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "title", "content", "writer"})
//@Table(name = "tbl_board")
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Column(length = 1000, nullable = false)
    private String content; // 내용

    @Column(length = 50, nullable = false)
    private String writer; // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public Board(String title, String content, String writer, Member member){
        this.title = title;
        this.content = content;
        this.writer = writer;
        if(member != null){
            changeMember(member);
        }
    }

    public void changeMember(Member member){
        this.member = member;
        member.getBoardList().add(this);
    }


    //update 메소드
    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }
}
