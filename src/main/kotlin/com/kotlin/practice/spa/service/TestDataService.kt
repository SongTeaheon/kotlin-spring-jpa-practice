package com.kotlin.practice.spa.service

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.repository.MemberRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class TestDataService(
    val memberRepository: MemberRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        for (i in 1..10) {
            val member = Member(name = "name$i")
            memberRepository.save(member)
        }
    }
}