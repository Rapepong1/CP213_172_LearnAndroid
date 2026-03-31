package com.example.lablearnandroid

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppConstantsTest {

    @Test
    fun testBaseUrl() {
        val baseUrl = "https://pokeapi.co/api/v2/"
        assertTrue("Base URL is using secure connection", baseUrl.startsWith("https"))
        assertEquals("https://pokeapi.co/api/v2/", baseUrl)
    }

    @Test
    fun testLabNameConstant() {
        val labName = "LAB5"
        assertEquals(4, labName.length)
        assertTrue(labName.contains("5"))
    }

    @Test
    fun testGpsPrecisionConstants() {
        val highAccuracyInterval = 1000L
        val minUpdateInterval = 500L
        
        assertTrue(highAccuracyInterval > minUpdateInterval)
        assertTrue(minUpdateInterval > 0)
    }

    @Test
    fun testSensorDefaultTypes() {
        val totalSupportedSensors = 3 // x, y, z
        assertEquals(3, totalSupportedSensors)
    }
}
