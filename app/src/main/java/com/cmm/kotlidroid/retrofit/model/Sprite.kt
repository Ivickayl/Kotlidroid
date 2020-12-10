package com.cmm.kotlidroid.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sprite(

    @Expose
    @SerializedName("front_default")
    val frontDefault: String
) : Serializable