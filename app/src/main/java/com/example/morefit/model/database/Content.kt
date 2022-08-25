package com.example.morefit.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content_table")
data class Content(
    @PrimaryKey var date: Long,
    var name: String,
    var time: Long,
    var reps: Int
)
