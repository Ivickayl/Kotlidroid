package com.cmm.kotlidroid.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StatSlot(

    @Expose
    @SerializedName("base_stat")
    val baseStat: Int,

    @Expose
    @SerializedName("effort")
    val effort: Int,

    @Expose
    @SerializedName("stat")
    val stat: Stat,
) : Serializable
