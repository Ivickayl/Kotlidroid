package com.cmm.kotlidroid.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "light_sensor_values")
data class LightStatusValue(
    val lux: Float,
    val date: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}