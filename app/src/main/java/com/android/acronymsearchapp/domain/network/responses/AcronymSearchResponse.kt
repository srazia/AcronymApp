package com.android.acronymsearchapp.domain.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AcronymSearchResponse {
    @SerializedName("sf")
    @Expose
    var sf: String? = null

    @SerializedName("lfs")
    @Expose
    var lfs: List<Lf>? = null
}