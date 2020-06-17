package com.therajanmaurya.core.models

import com.google.gson.annotations.SerializedName

data class Word(

    @SerializedName("text_eng")
    val textEng: String = "",

    @SerializedName("text_spa")
    val textSpaL: String = "",

    @Transient
    @SerializedName("score")
    var score: Int = 0,

    @Transient
    @SerializedName("answer")
    var answer: String = ""
)
