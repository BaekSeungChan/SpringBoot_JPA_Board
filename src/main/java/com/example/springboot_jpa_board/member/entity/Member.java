package com.example.springboot_jpa_board.member.entity;

import com.example.springboot_jpa_board.board.entity.Board;
import com.example.springboot_jpa_board.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "userId", "userPassword", "userName", "address", "phone", "gender"})
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId; // 아이디

    private String userPassword; // 비빌번호

    private String userName; // 이름

    private String address; // 주소

    private String phone; // 전화번호

    private String gender; // 성별

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

}
