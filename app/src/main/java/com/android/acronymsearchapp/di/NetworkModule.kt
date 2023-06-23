package com.android.acronymsearchapp.di

import com.android.acronymsearchapp.BuildConfig
import com.android.acronymsearchapp.domain.datasource.AcronymDataSourceImpl
import com.android.acronymsearchapp.domain.datasource.AcronymsDataSource
import com.android.acronymsearchapp.domain.network.ApiService
import com.android.acronymsearchapp.domain.network.DefaultDispatcherProvider
import com.android.acronymsearchapp.domain.network.DispatcherProvider
import com.android.acronymsearchapp.domain.repository.AcronymRepository
import com.android.acronymsearchapp.domain.repository.AcronymRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "http://www.nactem.ac.uk/software/acromine/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAcronymDataSource(acronymDataSourceImpl: AcronymDataSourceImpl): AcronymsDataSource =
        acronymDataSourceImpl

    @Provides
    @Singleton
    fun provideAcronymRepository(
        dispatcherProvider: DispatcherProvider,
        acronymsDataSource: AcronymsDataSource,
    ): AcronymRepository {
        return AcronymRepositoryImpl(dispatcherProvider, acronymsDataSource)
    }

    @Provides
    @Singleton
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

}