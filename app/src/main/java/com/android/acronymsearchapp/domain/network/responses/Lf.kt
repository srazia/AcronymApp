package com.android.acronymsearchapp.domain.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Lf {
    @SerializedName("lf")
    @Expose
    var lf: String? = null

    @SerializedName("freq")
    @Expose
    var freq: Int? = null

    @SerializedName("since")
    @Expose
    var since: Int? = null

    @SerializedName("vars")
    @Expose
    var vars: List<Var>? = null
}
