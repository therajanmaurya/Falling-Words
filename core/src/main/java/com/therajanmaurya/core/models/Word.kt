package com.therajanmaurya.core.models

import com.google.gson.annotations.SerializedName

data class Word(

    @SerializedName("text_eng")
    val textEng: String = "",

    @SerializedName("text_spa")
    val textSpaL: String = "",

    @Transient
    @SerializedName("score")
    val score: Int = 0
)
