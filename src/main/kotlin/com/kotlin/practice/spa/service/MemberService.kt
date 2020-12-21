package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberRepository: MemberRepository
) {
    fun getAllMembers(page: Int, size: Int): List<Member> {
        val pageRequest = PageRequest.of(page, size, Sort.by("id").descending())
        return memberRepository.findAll(pageRequest).toList()
    }

    fun changeName(memberId: Long, changedName: String): Member {
        val mem = memberRepository.findById(memberId).orElseThrow { RuntimeException() }
        mem.name = changedName

        return memberRepository.save(mem)
    }

    fun getMember(memberId: Long): Member {
        return memberRepository.findById(memberId).orElseThrow{ RuntimeException() }
    }
}