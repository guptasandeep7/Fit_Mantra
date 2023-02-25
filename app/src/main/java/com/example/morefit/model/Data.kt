package com.example.morefit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val category: String,
    val difficulty: String,
    val gender: String,
    val muscle: String,
    var counter:Boolean,
    var file_name:String?,
    var labels:List<String>?,
    var correct_label:String?,
    val text_tutorials: List<TextTutorial>,
    val title: String,
    val video_tutorials: List<String>
) : Parcelable