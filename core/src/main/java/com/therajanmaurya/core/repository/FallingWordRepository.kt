package com.therajanmaurya.core.repository

import com.therajanmaurya.core.api.FallingWordService
import com.therajanmaurya.core.models.Word
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FallingWordRepository @Inject constructor(private val fallingWordService: FallingWordService) {

    fun fetchWords(): List<Word>? {
        return fallingWordService.getWords().execute().body()
    }
}
