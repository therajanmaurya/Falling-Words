package com.therajanmaurya.fallingwords.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therajanmaurya.core.models.Word
import com.therajanmaurya.core.repository.FallingWordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: FallingWordRepository) :
    ViewModel() {

    var wordApiResult = MutableLiveData<List<Word>>().apply { value = emptyList() }
    var questionIndex = 0
    var suggestionIndex = 0
    var suggestionCount = 1
    var score = 0
    private var isFromNetwork = false
    var suggestionList: ArrayList<String> = arrayListOf()

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

    suspend fun fetchWords() {
        viewModelScope.launch {
            try {
                val words = repository.fetchWords()
                wordApiResult.postValue(words)
                isFromNetwork = true
            } catch (exception: Exception) {
                wordApiResult.value = repository.fetchFromLocalJson()
                wordApiResult.postValue(wordApiResult.value)
                isFromNetwork = false
            }
        }
    }
}
