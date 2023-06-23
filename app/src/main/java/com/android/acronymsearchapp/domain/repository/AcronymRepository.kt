package com.android.acronymsearchapp.domain.repository

import com.android.acronymsearchapp.domain.network.NetworkStatus
import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse

interface AcronymRepository {
    suspend fun getAcronymBySearch(searchItem: String): NetworkStatus<AcronymSearchResponse>
}