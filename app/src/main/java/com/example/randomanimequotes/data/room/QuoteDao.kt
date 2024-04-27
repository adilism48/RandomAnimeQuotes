package com.example.randomanimequotes.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert
    suspend fun insert(quoteDbModel: QuoteDbModel)

    @Delete
    suspend fun delete(quoteDbModel: QuoteDbModel)

    @Query("select * from quote_table where quote_id = :taskId")
    fun get(taskId: Long): LiveData<QuoteDbModel>

    @Query("select * from quote_table order by quote_id desc")
    fun getAll(): LiveData<List<QuoteDbModel>>
}