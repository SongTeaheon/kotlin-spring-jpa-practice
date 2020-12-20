package com.kotlin.practice.spa.test

import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserUpdaterTest {
    private lateinit var clientMock: UserClient

    @BeforeEach
    fun init() {
        clientMock = mockk()
    }

    @Test
    fun testTest() {

//        every { clientMock.getMember() } returns Member(id = 1, name = "kim")

        val updater = UserUpdater(clientMock)
        updater.updateUser(1)

//        verify { clientMock.getMember() }
    }
}