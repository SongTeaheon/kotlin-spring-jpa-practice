package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class MemberService(
    val memberRepository: MemberRepository
) {
    fun getAllMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun changeName(memberId: Long, changedName: String): Member {
        var mem = memberRepository.findById(memberId).orElseThrow { RuntimeException() }
        mem.name = changedName

        return memberRepository.save(mem)
    }
}