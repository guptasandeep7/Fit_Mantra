package com.example.morefit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TextTutorial(
    val text: String
) : Parcelable