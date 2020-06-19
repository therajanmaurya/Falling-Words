package com.therajanmaurya.core.jsonsource

import com.therajanmaurya.core.models.Word
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class JsonDataSourceTest {

    private val words = arrayListOf(
        Word("primary school", "escuela primaria"),
        Word("teacher", "profesor / profesora")
    )

    @Before
    fun setUp() {
        // Nothing to initialize
    }

    @Test
    fun getWords() {
        val jsonDataSourceWords = JsonDataSource.getWords(JsonName.WORDS_TEST)
        assertEquals(jsonDataSourceWords[0].textEng, words[0].textEng)
        assertEquals(jsonDataSourceWords[0].textSpaL, words[0].textSpaL)
        assertEquals(jsonDataSourceWords[1].textEng, words[1].textEng)
        assertEquals(jsonDataSourceWords[1].textEng, words[1].textEng)
        assertEquals(jsonDataSourceWords, words)
    }
}
