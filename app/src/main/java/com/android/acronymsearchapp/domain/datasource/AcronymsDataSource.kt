package com.android.acronymsearchapp.domain.datasource

import com.android.acronymsearchapp.domain.network.NetworkStatus
import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse

interface AcronymsDataSource {
    suspend fun getAcronymBySearch(searchItem: String): NetworkStatus<AcronymSearchResponse>
}