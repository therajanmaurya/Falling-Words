package com.therajanmaurya.fallingwords.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import com.therajanmaurya.core.api.FallingWordService
import com.therajanmaurya.core.models.Word
import com.therajanmaurya.core.repository.FallingWordRepository
import com.therajanmaurya.fallingwords.utils.LiveDataTestUtil
import com.therajanmaurya.fallingwords.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fallingWordService: FallingWordService
    private lateinit var fallingWordRepository: FallingWordRepository

    private lateinit var mainViewModel: MainViewModel

    private val words = arrayListOf(
        Word("primary school", "escuela primaria"),
        Word("teacher", "profesor / profesora")
    )

    @Before
    fun setUp() {
        fallingWordService = Mockito.mock(FallingWordService::class.java)
        fallingWordRepository = Mockito.mock(FallingWordRepository::class.java)
        runBlocking {
            whenever(fallingWordService.getWords()).thenReturn(words)
            whenever(fallingWordRepository.fetchWords()).thenReturn(words)
        }
        mainViewModel = MainViewModel(fallingWordRepository)
    }

    @Test
    fun test_fetch_words() {
        fetch_fake_words()
        LiveDataTestUtil.getValue(mainViewModel.wordApiResult) {
            assertThat(it.isNotEmpty())
            assertThat(it).hasSize(words.size)
            assertThat(it).containsExactlyElementsIn(words).inOrder()
        }
    }

    @Test
    fun test_current_question() {
        fetch_fake_words()
        mainViewModel.questionIndex = 0
        assertEquals(mainViewModel.currentQuestion, words[0])
    }

    private fun fetch_fake_words() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            mainViewModel.fetchWords()
        }
    }
}
