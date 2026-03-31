package com.example.lablearnandroid

import org.junit.Assert.assertEquals
import org.junit.Test

class SensorDataTest {

    @Test
    fun testDefaultSensorData() {
        val data = SensorData()
        assertEquals(0f, data.x, 0.001f)
        assertEquals(0f, data.y, 0.001f)
        assertEquals(0f, data.z, 0.001f)
    }

    @Test
    fun testCustomSensorData() {
        val data = SensorData(x = 1.5f, y = -2.0f, z = 9.8f)
        assertEquals(1.5f, data.x, 0.001f)
        assertEquals(-2.0f, data.y, 0.001f)
        assertEquals(9.8f, data.z, 0.001f)
    }
}
