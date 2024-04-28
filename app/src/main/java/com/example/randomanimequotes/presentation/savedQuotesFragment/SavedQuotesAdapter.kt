package com.example.randomanimequotes.presentation.savedQuotesFragment

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.randomanimequotes.data.room.QuoteDbModel

class SavedQuotesAdapter :
    ListAdapter<QuoteDbModel, SavedQuotesViewHolder>(SavedQuotesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedQuotesViewHolder {
        return SavedQuotesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SavedQuotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}