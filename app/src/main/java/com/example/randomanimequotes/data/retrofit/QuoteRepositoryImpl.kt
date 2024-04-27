package com.example.randomanimequotes.data.retrofit

import com.example.randomanimequotes.domain.QuoteRepository

class QuoteRepositoryImpl(private val quoteApiHelper: QuoteApiHelper): QuoteRepository {
    override suspend fun getQuote() = quoteApiHelper.getQuote()
}