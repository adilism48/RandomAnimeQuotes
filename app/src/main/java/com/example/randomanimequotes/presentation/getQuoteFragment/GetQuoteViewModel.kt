package com.example.randomanimequotes.presentation.getQuoteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.randomanimequotes.data.retrofit.QuoteRepositoryImpl
import com.example.randomanimequotes.data.room.QuoteDao
import com.example.randomanimequotes.data.room.QuoteDbModel
import com.example.randomanimequotes.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetQuoteViewModel(
    private val dao: QuoteDao,
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) : ViewModel() {
    var newQuote = ""
    var newCharacter = ""
    var newAnime = ""

    fun getQuoteFromApi() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data = quoteRepositoryImpl.getQuote()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun addQuote() {
        viewModelScope.launch {
            val quote = QuoteDbModel()
            quote.quoteText = newQuote
            quote.quoteAuthor = newCharacter
            quote.anime = newAnime
            dao.insert(quote)
        }
    }
}