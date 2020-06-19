package com.therajanmaurya.core.repository

import com.therajanmaurya.core.api.FallingWordService
import com.therajanmaurya.core.models.Word
import com.therajanmaurya.core.jsonsource.JsonDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class FallingWordRepository @Inject constructor(private val fallingWordService: FallingWordService) {

    suspend fun fetchWords(): List<Word> {
        return try {
            fallingWordService.getWords()
        } catch (e: Exception) {
            fetchFromLocalJson()
        }
    }

    open fun fetchFromLocalJson(): List<Word> {
        return JsonDataSource.getWords()
    }
}
