package com.example.springboot_jpa_board.QueryTest;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.springboot_jpa_board.QueryTest.QMemberEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @PersistenceContext
    private EntityManager em;

    JPAQueryFactory queryFactory;


    @BeforeEach
    public void testEntity(){
        queryFactory = new JPAQueryFactory(em);


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
    }

    @Test
    public void JpqlTest(){
        String qlString =
                "select m from MemberEntity m " +
                        "where m.username = :username";

        MemberEntity findMember= em.createQuery(qlString, MemberEntity.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }


    @Test
    public void startQuerydsl(){

        MemberEntity findMember = queryFactory
                .select(memberEntity)
                .from(memberEntity)
                .where(memberEntity.username.eq("member1")) // 파라미터 바인딩 처리
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search(){

        MemberEntity findMember = queryFactory
                .selectFrom(memberEntity)
                .where(memberEntity.username.eq("member1")
                        .and(memberEntity.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam(){
        List<MemberEntity> result1 = queryFactory
                .selectFrom(memberEntity)
                .where(memberEntity.username.eq("member1"),
                        memberEntity.age.eq(10)
                ).fetch();

        assertThat(result1.size()).isEqualTo(1);
    }


    @Test
    public void searchFind(){
        // list
        List<MemberEntity> fetch = queryFactory
                .selectFrom(memberEntity)
                .fetch();

        for(MemberEntity member : fetch){
            System.out.println("memberfindAll : " + member.getUsername());
        }


        // 단 건
        MemberEntity findMember1 = queryFactory
                .selectFrom(memberEntity)
                .where(memberEntity.username.eq("member1"))
                .fetchOne();

        System.out.println("findMember1 : " + findMember1);


        // 페이징에서 사용
        QueryResults<MemberEntity> findMember2 = queryFactory
                .selectFrom(memberEntity)
                .fetchResults();

        // count 쿼리로ㅗ 변경
        long count = queryFactory
                .selectFrom(memberEntity)
                .fetchCount();

        System.out.println(count);
    }

    /*
    * 회원 정렬 순서
    * 1. 회원 나이 내림차순(desc)
    * 2. 회원 이름 올림차순(asc)
    * 단 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
    * */

    @Test
    public void sort(){
        em.persist(new MemberEntity(null, 100));
        em.persist(new MemberEntity("member5", 100));
        em.persist(new MemberEntity("member6", 100));

        List<MemberEntity> result = queryFactory
                .selectFrom(memberEntity)
                .orderBy(memberEntity.age.desc(), memberEntity.username.asc().nullsLast())
                .fetch();

        MemberEntity member5 = result.get(0);
        MemberEntity member6 = result.get(1);

        MemberEntity memberNull = result.get(2);
        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();

    }


    @Test
    public void paging1(){
        List<MemberEntity> result = queryFactory
                .selectFrom(memberEntity)
                .orderBy(memberEntity.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }


    @Test
    public void paging2(){
        QueryResults<MemberEntity> queryResults = queryFactory
                .selectFrom(memberEntity)
                .orderBy(memberEntity.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }
}
