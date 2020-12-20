package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberServiceTest {

    private val memberRepository: MemberRepository = mockk()
    private val memberService = MemberService(memberRepository)

    @Test
    fun `test getAllMembers method_회원이 없는 경우`() {
        //when
        every { memberRepository.findAll() } returns emptyList()

        //then
        Assertions.assertIterableEquals(emptyList<Member>(), memberService.getAllMembers())
    }

    @Test
    fun `test getAllMembers method_회원이 있는 경우`() {
        //given
        val list = listOf(Member(id = 1, name = "kim"), Member(id = 2, name = "song"), Member(id = 3, name = "chang"))

        //when
        every { memberRepository.findAll() } returns list

        //then
        Assertions.assertIterableEquals(list, memberService.getAllMembers())
    }

    @Test
    fun `test 이름변경`() {
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
    fun `test 이름변경 없는 id인 경`() {
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