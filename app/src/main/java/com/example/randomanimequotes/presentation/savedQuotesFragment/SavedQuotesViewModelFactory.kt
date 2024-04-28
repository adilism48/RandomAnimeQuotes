package com.example.randomanimequotes.presentation.savedQuotesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.randomanimequotes.data.room.QuoteDao

class SavedQuotesViewModelFactory(private val dao: QuoteDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(SavedQuotesViewModel::class.java)) {
            return SavedQuotesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}