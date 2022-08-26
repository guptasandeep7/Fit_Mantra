package com.example.morefit.model.database

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class meal(
    @PrimaryKey(autoGenerate = false)
    var id:String,
    var image: String,
    var title: String,
    var serving:String,
    var cal: String,
    var roti:String,
    var rice:String,
    var url:String
    )
