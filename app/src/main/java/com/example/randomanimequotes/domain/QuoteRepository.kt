package com.example.randomanimequotes.domain

interface QuoteRepository {
    suspend fun getQuote(): Quote
}