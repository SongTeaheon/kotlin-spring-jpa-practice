package com.kotlin.practice.spa.repository

import com.kotlin.practice.spa.entity.Member
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.PageRequest
import javax.persistence.EntityManager
import javax.transaction.Transactional

@DataJpaTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    lateinit var em: TestEntityManager

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    @Transactional
    fun `findAll 테스트_paging`() {
        //given
        val m1 = Member(name = "song")
        val m2 = Member(name = "kim")
        em.persist(m1)
        em.persist(m2)

        //when
        var pageRequest = PageRequest.of(0, 1)
        val allMembers = memberRepository.findAll(pageRequest)

        //then
        Assertions.assertEquals(1, allMembers.size)
        Assertions.assertEquals(listOf(m1), allMembers.toList())
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