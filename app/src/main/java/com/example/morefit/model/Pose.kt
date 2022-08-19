package com.example.morefit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pose(
		val english_name: String,
		val sanskrit_name: String,
		val yoga_description: String,
		val image_url: String,
		val yoga_instruction: List<String>
) : Parcelable