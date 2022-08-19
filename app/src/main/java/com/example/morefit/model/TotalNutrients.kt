package com.example.morefit.model

import com.google.gson.annotations.SerializedName

data class TotalNutrients(
    val CA: CHOLEX,
    val CHOCDF: CHOLEX,
    @SerializedName("CHOCDF.net") var CHOCDF2: CHOLEX,
    val CHOLE: CHOLEX,
    val ENERC_KCAL: CHOLEX,
    val FAMS: CHOLEX,
    val FAPU: CHOLEX,
    val FASAT: CHOLEX,
    val FAT: CHOLEX,
    val FATRN: CHOLEX,
    val FE: CHOLEX,
    val FIBTG: CHOLEX,
    val FOLAC: CHOLEX,
    val FOLDFE: CHOLEX,
    val FOLFD: CHOLEX,
    val K: CHOLEX,
    val MG: CHOLEX,
    val NA: CHOLEX,
    val NIA: CHOLEX,
    val P: CHOLEX,
    val PROCNT: CHOLEX,
    val RIBF: CHOLEX,
    val SUGAR: CHOLEX,
    @SerializedName("SUGAR.added") val SUGAR2: CHOLEX,
    @SerializedName("Sugar.alcohol")val Sugar3: CHOLEX,
    val THIA: CHOLEX,
    val TOCPHA: CHOLEX,
    val VITA_RAE: CHOLEX,
    val VITB12: CHOLEX,
    val VITB6A: CHOLEX,
    val VITC: CHOLEX,
    val VITD: CHOLEX,
    val VITK1: CHOLEX,
    val WATER: CHOLEX,
    val ZN: CHOLEX
)