package com.cmm.kotlidroid.android.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import com.cmm.kotlidroid.android.model.LightStatusValue
import com.cmm.kotlidroid.architecture.CustomApplication
import java.time.Instant

class SensorRepository : SensorEventListener {

    private val lightStatusDao =
        CustomApplication.instance.applicationDatabase.lightSensorValueDao()
    private val sensorManager by lazy {
        CustomApplication.instance.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private lateinit var sensor: Sensor
    private lateinit var lightStatusValue: LightStatusValue
    val lightStatusMutableLiveData = MutableLiveData<LightStatusValue>()
    val dbLightStatusMutableLiveData = lightStatusDao.selectAll()

    init {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            lightStatusValue = LightStatusValue(
                event.values[0],
                Instant.now().toString()
            )

            lightStatusMutableLiveData.postValue(lightStatusValue)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    fun insert(lightValue: LightStatusValue) {
        lightStatusDao.insert(lightValue)
    }

    fun deleteAll() {
        lightStatusDao.deleteAll()
    }

    fun delete(item: LightStatusValue) {
        lightStatusDao.delete(item)
    }
}