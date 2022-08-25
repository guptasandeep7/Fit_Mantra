package com.example.morefit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.morefit.model.database.meal

@Dao
interface mealDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMealData(lateEntry: meal)

    @Query("SELECT * FROM meal_table")
    suspend fun getMealData(): List<meal>

    @Query("DELETE FROM meal_table")
    suspend fun deleteMealData()

    @Query("SELECT Count(id) FROM meal_table")
    suspend fun getCount(): Int
}