package com.cmm.kotlidroid.android.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cmm.kotlidroid.android.model.LightStatusValue

@Dao
interface LightStatusDao {

    @Query("SELECT * FROM light_sensor_values ORDER BY id ASC")
    fun selectAll(): LiveData<MutableList<LightStatusValue>>

    @Insert
    fun insert(lightSensorValue: LightStatusValue)

    @Delete
    fun delete(lightSensorValue: LightStatusValue)

    @Query("DELETE FROM light_sensor_values")
    fun deleteAll()

}
