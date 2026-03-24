package com.example.lablearnandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorViewModel(application: Application) : AndroidViewModel(application) {

    // MutableStateFlow for internal updates
    private val _sensorData = MutableStateFlow(SensorData())

    // Immutable StateFlow for UI to observe
    val sensorData: StateFlow<SensorData> = _sensorData.asStateFlow()

    private val sensorTracker = SensorTracker(application)

    fun startTracking() {
        sensorTracker.startTracking { data ->
            _sensorData.value = data
        }
    }

    fun stopTracking() {
        sensorTracker.stopTracking()
    }

    override fun onCleared() {
        super.onCleared()
        stopTracking()
    }
}
