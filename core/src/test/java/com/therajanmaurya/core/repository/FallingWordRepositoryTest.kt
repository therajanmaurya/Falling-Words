package com.therajanmaurya.core.repository

import com.nhaarman.mockitokotlin2.whenever
import com.therajanmaurya.core.api.FallingWordService
import com.therajanmaurya.core.jsonsource.JsonName
import com.therajanmaurya.core.models.Word
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class FallingWordRepositoryTest {

    private lateinit var fallingWordService: FallingWordService
    private lateinit var fallingWordRepository: FallingWordRepository

    private val words = arrayListOf(
        Word("primary school", "escuela primaria"),
        Word("teacher", "profesor / profesora")
    )

    @Before
    fun setUp() {
        fallingWordService = mock(FallingWordService::class.java)
        runBlocking {
            whenever(fallingWordService.getWords()).thenReturn(words)
        }

        fallingWordRepository = FallingWordRepository(fallingWordService)
    }

    @Test
    fun fetch_words() {
        runBlocking {
            assertEquals(words, fallingWordRepository.fetchWords())
        }
    }

    @Test
    fun fetch_from_local_Json() {
        assertEquals(words, fallingWordRepository.fetchFromLocalJson(JsonName.WORDS_TEST))
    }
}
