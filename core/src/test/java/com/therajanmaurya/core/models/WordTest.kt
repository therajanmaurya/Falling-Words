package com.therajanmaurya.core.models

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WordTest {

    private lateinit var word: Word

    @Before
    fun setUp() {
        word = Word("teacher", "profesor / profesora")
    }

    @Test
    fun test_default_values() {
        val defaultWord = Word("primary school", "escuela primaria")
        assertEquals("primary school", defaultWord.textEng)
        assertEquals("escuela primaria", defaultWord.textSpaL)
        assertEquals(0, defaultWord.score)
        assertEquals("", defaultWord.answer)
    }
}
