package com.cmm.kotlidroid.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Type(

    @Expose
    @SerializedName("name")
    val name: String

) : Serializable