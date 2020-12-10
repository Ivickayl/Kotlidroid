package com.cmm.kotlidroid.architecture

import androidx.room.TypeConverter
import com.cmm.kotlidroid.retrofit.model.StatSlot
import com.cmm.kotlidroid.retrofit.model.TypeSlot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringToArraySlot(listOfTypeSlot: String): List<TypeSlot> =
        Gson().fromJson(listOfTypeSlot, object : TypeToken<List<TypeSlot>>() {}.type)

    @TypeConverter
    fun fromArrayToString(listOfTypeSlot: List<TypeSlot>): String =
        Gson().toJson(listOfTypeSlot)

    @TypeConverter
    fun fromStringToArrayTypeSlot(listOfTypeSlot: String): List<StatSlot> =
        Gson().fromJson(listOfTypeSlot, object : TypeToken<List<StatSlot>>() {}.type)

    @TypeConverter
    fun fromArrayTypeSLotToString(listOfTypeSlot: List<StatSlot>): String =
        Gson().toJson(listOfTypeSlot)
}

