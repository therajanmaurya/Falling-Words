package com.therajanmaurya.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Words : ArrayList<Word>(), Parcelable
