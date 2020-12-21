package com.kotlin.practice.spa.test

import com.kotlin.practice.spa.entity.Member
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import kotlin.streams.toList

class KotlinTest {
    @Test
    fun `instance equals test`() {
        Assertions.assertEquals(Member(id = 1, name = "kim"), Member(id = 1, name = "kim"))
    }

    @Test
    fun `page interface test`() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        val pageRequest = PageRequest.of(0, 3)
        val page = PageImpl<Int>(list.subList(0, 3), pageRequest, list.size.toLong())

        Assertions.assertEquals(3, page.size)
        Assertions.assertIterableEquals(listOf(1, 2, 3), page.content)
    }
}