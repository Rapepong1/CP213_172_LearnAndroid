package com.example.lablearnandroid

import org.junit.Assert.assertEquals
import org.junit.Test

class GpsDataTest {

    @Test
    fun testDefaultGpsData() {
        val gpsData = GpsData()
        assertEquals(0.0, gpsData.lat, 0.001)
        assertEquals(0.0, gpsData.lng, 0.001)
    }

    @Test
    fun testCustomGpsData() {
        // Arrange
        val expectedLat = 13.736717
        val expectedLng = 100.523186
        
        // Act
        val gpsData = GpsData(lat = expectedLat, lng = expectedLng)
        
        // Assert
        assertEquals(expectedLat, gpsData.lat, 0.001)
        assertEquals(expectedLng, gpsData.lng, 0.001)
    }
}
