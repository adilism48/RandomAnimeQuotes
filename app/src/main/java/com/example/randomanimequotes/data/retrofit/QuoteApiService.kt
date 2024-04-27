package com.example.randomanimequotes.data.retrofit

import com.example.randomanimequotes.domain.Quote
import retrofit2.http.GET

interface QuoteApiService {
    @GET("api/random")
    suspend fun getQuote(): Quote
}