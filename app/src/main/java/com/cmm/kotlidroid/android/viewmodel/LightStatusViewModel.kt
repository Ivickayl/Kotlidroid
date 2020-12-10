package com.cmm.kotlidroid.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmm.kotlidroid.android.model.LightStatusValue
import com.cmm.kotlidroid.android.repository.SensorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant

class LightStatusViewModel : ViewModel() {

    private val sensorRepository: SensorRepository by lazy { SensorRepository() }

    val lightMutableLiveData = sensorRepository.lightStatusMutableLiveData
    val dbLightMutableLiveData = sensorRepository.dbLightStatusMutableLiveData

    fun addData(lux: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            sensorRepository.insert(LightStatusValue(lux, Instant.now().toString()))
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            sensorRepository.deleteAll()
        }
    }

    fun delete(item: LightStatusValue) {
        viewModelScope.launch(Dispatchers.IO) {
            sensorRepository.delete(item)
        }
    }
}