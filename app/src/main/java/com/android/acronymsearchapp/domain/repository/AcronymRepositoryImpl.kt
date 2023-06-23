package com.android.acronymsearchapp.domain.repository

import com.android.acronymsearchapp.domain.datasource.AcronymsDataSource
import com.android.acronymsearchapp.domain.network.DispatcherProvider
import com.android.acronymsearchapp.domain.network.NetworkStatus
import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AcronymRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val acronymsDataSource: AcronymsDataSource
) : AcronymRepository {
    override suspend fun getAcronymBySearch(searchItem: String): NetworkStatus<AcronymSearchResponse> {
        return withContext(dispatcherProvider.io()) { acronymsDataSource.getAcronymBySearch(searchItem) }
    }

}