package com.therajanmaurya.core.jsonsource

import com.google.gson.reflect.TypeToken
import com.therajanmaurya.core.models.Word

/**
 * JsonDataSource is reading the local json files into the java object using gson.
 */
object JsonDataSource {

    private val dataFactory = DataFactory()

    fun getWords(jsonName: String): List<Word> {
        return dataFactory.convertJsonToDataObject(
            object : TypeToken<List<Word>>() {
            }, jsonName
        )
    }
}
