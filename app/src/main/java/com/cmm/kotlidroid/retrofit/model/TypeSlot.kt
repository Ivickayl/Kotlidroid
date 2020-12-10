package com.cmm.kotlidroid.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TypeSlot(

    @Expose
    @SerializedName("slot")
    val slot: Int,

    @Expose
    @SerializedName("type")
    val type: Type
) : Serializable