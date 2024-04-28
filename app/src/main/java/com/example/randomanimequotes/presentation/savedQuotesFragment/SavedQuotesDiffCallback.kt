package com.example.randomanimequotes.presentation.savedQuotesFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.randomanimequotes.data.room.QuoteDbModel

class SavedQuotesDiffCallback : DiffUtil.ItemCallback<QuoteDbModel>() {
    override fun areItemsTheSame(oldItem: QuoteDbModel, newItem: QuoteDbModel): Boolean {
        return oldItem.quoteId == newItem.quoteId
    }

    override fun areContentsTheSame(oldItem: QuoteDbModel, newItem: QuoteDbModel): Boolean {
        return oldItem == newItem
    }
}