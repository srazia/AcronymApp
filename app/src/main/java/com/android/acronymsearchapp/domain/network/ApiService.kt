package com.android.acronymsearchapp.domain.network

import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("dictionary.py")
    suspend fun getAcronymBySearch(
        @Query("sf") searchItem: String
    ): Response<AcronymSearchResponse>

}