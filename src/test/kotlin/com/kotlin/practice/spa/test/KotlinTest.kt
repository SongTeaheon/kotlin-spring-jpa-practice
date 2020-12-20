package com.kotlin.practice.spa.test

import com.kotlin.practice.spa.entity.Member
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KotlinTest {
    @Test
    fun `instance equals test`() {
        Assertions.assertEquals(Member(id = 1, name = "kim"), Member(id = 1, name = "kim"))
    }
}