package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberServiceTest {

    private val memberRepository: MemberRepository = mockk()
    private val memberService = MemberService(memberRepository)

    @Nested
    inner class GetAllMemberTest {
        @Test
        fun `회원이 없는 경우`() {
            //when
            every { memberRepository.findAll() } returns emptyList()

            //then
            Assertions.assertIterableEquals(emptyList<Member>(), memberService.getAllMembers())
        }

        @Test
        fun `회원이 있는 경우`() {
            //given
            val list = listOf(Member(id = 1, name = "kim"), Member(id = 2, name = "song"), Member(id = 3, name = "chang"))

            //when
            every { memberRepository.findAll() } returns list

            //then
            Assertions.assertIterableEquals(list, memberService.getAllMembers())
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