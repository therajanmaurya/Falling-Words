package com.therajanmaurya.fallingwords.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therajanmaurya.core.models.Word
import com.therajanmaurya.core.repository.FallingWordRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: FallingWordRepository) :
    ViewModel() {

    var wordApiResult = MutableLiveData<List<Word>>().apply { value = emptyList() }
    var questionIndex = 0
    var suggestionIndex = 0
    var suggestionCount = 1
    private var isFromNetwork = false
    var suggestionList: ArrayList<String> = arrayListOf()
    var attemptedQuestions: ArrayList<Word> = arrayListOf()

    var rightAnswerCount = 0
    var wrongAnswerCount = 0
    var unAnsweredCount = 0

    init {
        viewModelScope.launch { fetchWords() }
    }

    val isFetchedFromNetwork = isFromNetwork && wordApiResult.value!!.isNotEmpty()

    fun nextQuestion(): Word {
        ++questionIndex
        buildSuggestionList()
        return currentQuestion
    }

    val currentQuestion: Word
        get() = wordApiResult.value!![questionIndex]

    fun buildSuggestionList() {
        synchronized(this) {
            suggestionList.clear()
            suggestionIndex = 0
            suggestionCount = 1
            val currentAnswer = currentQuestion.textSpaL
            suggestionList.add(currentAnswer)
            while (true) {
                val randomSpa = wordApiResult.value!!.random().textSpaL
                if (randomSpa == currentAnswer) {
                    continue
                } else {
                    suggestionList.add(randomSpa)
                }
                if (suggestionList.size == 4) {
                    break
                }
            }
            suggestionList.shuffle()
        }
    }

    val currentSuggestion: String get() = suggestionList[0]

    fun nextSuggestion(): String {
        ++suggestionIndex
        ++suggestionCount
        return suggestionList[suggestionIndex]
    }

    fun addAttemptedQuestion(attempt: Int, answer: String = "") {
        attemptedQuestions.add(currentQuestion.apply {
            this.score = attempt
            if (answer.isEmpty()) {
                this.answer = textSpaL
            } else {
                this.answer = answer
            }
        })
    }

    suspend fun fetchWords() {
        viewModelScope.launch {
            try {
                val words = repository.fetchWords()
                wordApiResult.postValue(words.shuffled())
                isFromNetwork = true
            } catch (exception: Exception) {
                wordApiResult.value = repository.fetchFromLocalJson().shuffled()
                wordApiResult.postValue(wordApiResult.value)
                isFromNetwork = false
            }
        }
    }
}