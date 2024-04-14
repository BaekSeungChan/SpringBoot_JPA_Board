package com.example.springboot_jpa_board.QueryTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberEntityTest {
    @PersistenceContext
    private EntityManager em;


    @Test
    public void testEntity(){
        Team teamA = new Team("teatA");
        Team teamB = new Team("teatB");
        em.persist(teamA);
        em.persist(teamB);

        MemberEntity member1 = new MemberEntity("member1", 10, teamA);
        MemberEntity member2 = new MemberEntity("member2", 20, teamA);
        MemberEntity member3 = new MemberEntity("member3", 30, teamB);
        MemberEntity member4 = new MemberEntity("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<MemberEntity> members = em.createQuery("select m from MemberEntity  m", MemberEntity.class)
                .getResultList();


        for(MemberEntity member : members){
            System.out.println("member = " + member);
            System.out.println("member.team "  + member.getTeam());
        }
    }

}