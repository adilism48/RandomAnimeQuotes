package com.example.randomanimequotes.presentation.getQuoteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomanimequotes.data.retrofit.QuoteApiHelper
import com.example.randomanimequotes.data.retrofit.QuoteRepositoryImpl
import com.example.randomanimequotes.data.room.QuoteDao

class GetQuoteViewModelFactory(
    private val dao: QuoteDao,
    private val quoteApiHelper: QuoteApiHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetQuoteViewModel::class.java)) {
            return GetQuoteViewModel(dao, QuoteRepositoryImpl(quoteApiHelper)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}