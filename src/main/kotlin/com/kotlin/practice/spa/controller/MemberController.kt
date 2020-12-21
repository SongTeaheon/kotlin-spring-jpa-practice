package com.kotlin.practice.spa.controller

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    val memberService: MemberService
) {
    @GetMapping
    fun getAllMembers(page: Int, size: Int): List<Member> {
        return memberService.getAllMembers(page - 1, size) //controller는 page가 1부터 시작.
    }

}