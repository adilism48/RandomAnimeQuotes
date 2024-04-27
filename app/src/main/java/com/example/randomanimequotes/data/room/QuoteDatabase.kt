package com.example.randomanimequotes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuoteDbModel::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {
    abstract val quoteDao: QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "quotes_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}