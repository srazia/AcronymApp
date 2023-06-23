package com.android.acronymsearchapp.domain.datasource

import com.android.acronymsearchapp.domain.network.ApiService
import com.android.acronymsearchapp.domain.network.NetworkStatus
import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse
import com.android.acronymsearchapp.domain.network.safeApiCall
import javax.inject.Inject

class AcronymDataSourceImpl @Inject constructor (private val apiService: ApiService) : AcronymsDataSource {
    override suspend fun getAcronymBySearch(searchItem: String): NetworkStatus<AcronymSearchResponse> {
        return safeApiCall {apiService.getAcronymBySearch(searchItem)}
    }

}