package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberRepository: MemberRepository
) {
    fun getAllMembers(): List<Member> {
        return memberRepository.findAll()
    }
}