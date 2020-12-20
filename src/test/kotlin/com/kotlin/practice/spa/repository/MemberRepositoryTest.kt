package com.kotlin.practice.spa.repository

import com.kotlin.practice.spa.entity.Member
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import javax.persistence.EntityManager
import javax.transaction.Transactional

@DataJpaTest
@Transactional
class MemberRepositoryTest(
    @Autowired
    val em: TestEntityManager,
    @Autowired
    val memberRepository: MemberRepository
) {

    @Test
    @Transactional
    fun `findAll 테스트 (영속성 컨텍스트에만 있는 경우)`() {
        //given
        val m1 = Member(name = "song")
        val m2 = Member(name = "kim")
        em.persist(m1)
        em.persist(m2)

        //when
        val allMembers = memberRepository.findAll()

        //then
        Assertions.assertEquals(2, allMembers.size)
    }

    @Test
    fun `findAll 테스트 (DB 에만 있는 경우)`() {
        //given
        em.persist(Member(name = "song"))
        em.persist(Member(name = "kim"))
        em.flush()
        em.clear()

        //when
        val allMembers = memberRepository.findAll()

        //then
        Assertions.assertEquals(2, allMembers.size)
    }

    @Test
    fun `findAll 테스트 (DB와 영속성 컨텍스트에 있는 경우)`() {
        //given
        em.persist(Member(name = "song"))
        em.persist(Member(name = "kim"))
        em.flush()

        //when
        val allMembers = memberRepository.findAll()

        //then
        Assertions.assertEquals(2, allMembers.size)
    }
}