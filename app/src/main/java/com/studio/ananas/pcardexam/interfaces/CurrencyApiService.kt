package com.studio.ananas.pcardexam.interfaces

import com.studio.ananas.pcardexam.entities.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String,
        @Query("currencies") currencies: String = "EUR,USD,CAD,BGN,CNY,SEK,JPY,KRW,DKK,HUF",
        @Query("base_currency") baseCurrency: String? = null
    ): CurrencyResponse
}