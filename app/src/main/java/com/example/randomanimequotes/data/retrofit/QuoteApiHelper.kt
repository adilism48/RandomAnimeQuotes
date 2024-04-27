package com.example.randomanimequotes.data.retrofit

class QuoteApiHelper(private val quoteApiService: QuoteApiService) {
    suspend fun getQuote() = quoteApiService.getQuote()
}