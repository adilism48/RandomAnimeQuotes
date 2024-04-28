package com.example.randomanimequotes.presentation.savedQuotesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomanimequotes.data.room.QuoteDbModel
import com.example.randomanimequotes.databinding.QuoteListItemBinding

class SavedQuotesViewHolder(private val binding: QuoteListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): SavedQuotesViewHolder {
            return SavedQuotesViewHolder(
                QuoteListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(item: QuoteDbModel) = with(binding) {
        tvQuote.text = item.quoteText
        tvCharacter.text = item.quoteAuthor
        tvAnime.text = item.anime
    }
}