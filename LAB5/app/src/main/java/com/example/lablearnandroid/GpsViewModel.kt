package com.example.lablearnandroid

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GpsViewModel(application: Application) : AndroidViewModel(application) {
    private val _gpsData = MutableStateFlow(GpsData())
    val gpsData: StateFlow<GpsData> = _gpsData.asStateFlow()

    private val gpsTracker = GpsTracker(application)

    fun startTracking() {
        gpsTracker.startTracking { location: Location ->
            _gpsData.value = GpsData(location.latitude, location.longitude)
        }
    }

    fun stopTracking() {
        gpsTracker.stopTracking()
    }

    override fun onCleared() {
        super.onCleared()
        stopTracking()
    }
}
