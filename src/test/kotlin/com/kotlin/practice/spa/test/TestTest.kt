package com.kotlin.practice.spa.test

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestTest {
    @Test
    fun `test test`() {
        assertThat(0.03f).isCloseTo(0.03f, Offset.offset(0.001f))
    }
}