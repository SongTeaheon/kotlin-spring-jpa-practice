package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.data.domain.*
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberServiceTest {

    private val memberRepository: MemberRepository = mockk()
    private val memberService = MemberService(memberRepository)

    @Nested
    inner class GetAllMemberTest {
        @Test
        fun `회원이 없는 경우`() {
            //givne
            val pageRequest = PageRequest.of(1, 10, Sort.by("id").descending())

            //when
            every { memberRepository.findAll(pageRequest) } returns Page.empty()

            //then
            Assertions.assertIterableEquals(emptyList<Member>(), memberService.getAllMembers(1, 10))
        }

        @Test
        fun `회원이 있는 경우`() {
            //given
            val list =
                listOf(
                    Member(id = 1, name = "kim"),
                    Member(id = 2, name = "song"),
                    Member(id = 3, name = "chang"),
                    Member(id = 4, name = "kim"),
                    Member(id = 5, name = "song"),
                    Member(id = 6, name = "chang")
                )
            val pageRequest = PageRequest.of(0, 3, Sort.by("id").descending())

            //when
            val page = PageImpl(list.subList(0, 3), pageRequest, list.size.toLong())
            every { memberRepository.findAll(pageRequest) } returns page

            //then
            Assertions.assertIterableEquals(list.subList(0, 3), memberService.getAllMembers(0, 3))
        }
    }

    @Nested
    inner class ChangeNameTest {
        @Test
        fun `id가 있는 경우`() {
            //given
            val changedName = "chang"
            val memBefore = Member(id = 1L, name = "song")

            every { memberRepository.findById(1L) } returns Optional.ofNullable(memBefore)
            every { memberRepository.save(memBefore) } returns Member(id = 1L, name = changedName)

            //when
            val memAfter = memberService.changeName(memBefore.id!!, changedName)

            //then
            Assertions.assertEquals(memBefore, memAfter)
            Assertions.assertEquals(changedName, memAfter.name)
        }

        @Test
        fun `id가 잘못된 경우`() {
            //given
            val changedName = "chang"
            val memBefore = Member(id = 1L, name = "song")

            every { memberRepository.findById(1L) } returns Optional.empty()
            //when-then

            Assertions.assertThrows(
                RuntimeException::class.java
            ) { memberService.changeName(memBefore.id!!, changedName) }
        }
    }

    @Nested
    inner class GetMemberTest {

        @Test
        fun `id가 존재하는 경우`() {
            //given
            val member = Member(id = 1L, name = "song")

            //when
            every { memberRepository.findById(member.id!!) } returns Optional.of(member)

            Assertions.assertEquals(member, memberService.getMember(member.id!!))
        }

        @Test
        fun `id가 존재하지 않는 경우`() {
            //given
            val member = Member(id = 1L, name = "song")
            //when
            every { memberRepository.findById(member.id!!) } returns Optional.empty()

            Assertions.assertThrows(
                RuntimeException::class.java
            ) { memberService.getMember(member.id!!) }
        }
    }

}