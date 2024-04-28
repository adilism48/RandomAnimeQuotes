package com.example.randomanimequotes.presentation.savedQuotesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomanimequotes.data.room.QuoteDao
import com.example.randomanimequotes.data.room.QuoteDbModel
import kotlinx.coroutines.launch

class SavedQuotesViewModel(private val dao: QuoteDao) : ViewModel() {
    val quotesFromDb = dao.getAll()

    fun deleteQuote(quoteDbModel: QuoteDbModel) {
        viewModelScope.launch {
            dao.delete(quoteDbModel)
        }
    }
}