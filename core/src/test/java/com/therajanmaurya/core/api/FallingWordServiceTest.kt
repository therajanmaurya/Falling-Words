package com.therajanmaurya.core.api

import com.nhaarman.mockitokotlin2.whenever
import com.therajanmaurya.core.models.Word
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class FallingWordServiceTest {

    private lateinit var fallingWordService: FallingWordService

    private val words = arrayListOf(
        Word("primary school", "escuela primaria"),
        Word("teacher", "profesor / profesora")
    )

    @Before
    fun setUp() {
        fallingWordService = Mockito.mock(FallingWordService::class.java)
    }

    @Test
    fun getWords() {
        runBlocking {
            whenever(fallingWordService.getWords()).thenReturn(words)
        }
    }
}
