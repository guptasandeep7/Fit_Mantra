package com.example.morefit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.morefit.model.database.Content
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDAO {

    @Query("SELECT * FROM content_table ")
    fun getAlphabetizedWords(): Flow<List<Content>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data:MutableList<Content>)

    @Query("DELETE FROM content_table")
    suspend fun deleteAll()


}