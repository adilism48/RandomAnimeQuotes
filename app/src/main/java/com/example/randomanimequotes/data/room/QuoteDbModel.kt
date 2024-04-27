package com.example.randomanimequotes.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class QuoteDbModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quote_id")
    var quoteId: Long = 0L,

    @ColumnInfo(name = "quote_text")
    var quoteText: String = "",

    @ColumnInfo(name = "quote_author")
    var quoteAuthor: String = "",

    var anime: String = ""
)