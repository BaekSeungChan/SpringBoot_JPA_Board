package com.example.springboot_jpa_board.QueryTest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Team team;

    public MemberEntity(String username){
        this(username, 0);
    }
    public MemberEntity(String username, int age){
        this(username, age, null);
    }

    public MemberEntity(String username, int age, Team team){
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }


    public void changeTeam(Team team){
        this.team = team;
        team.getMemberList().add(this);
    }

}