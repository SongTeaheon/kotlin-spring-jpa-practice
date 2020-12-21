/*
 * Copyright (c) 2019. LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.kotlin.practice.spa.controller

import com.kotlin.practice.spa.entity.Member
import com.kotlin.practice.spa.service.MemberService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


/**
 * @author tae-heon.song<taeheon.song@linecorp.com>
 * @since 2020. 12. 21.
 */

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var memberService: MemberService

    @Nested
    inner class TestGetAllMember {
        @Test
        fun `with paging`() {
            //when
            mockMvc.perform(
                get("/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("size", "3")
                    .param("page", "1")
            ).andDo(print())
                .andExpect(jsonPath("$", hasSize<Any>(3)))
        }

        @Test
        fun `no paging`() {
            //given
            val sizeExpected = memberService.getAllMembers(null, null).size

            //when
            mockMvc.perform(
                get("/members")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                .andExpect(jsonPath("$", hasSize<Any>(sizeExpected)))
        }
    }


}