package com.example.lablearnandroid

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringFormatterTest {

    @Test
    fun testFormatGpsCoordinate() {
        val lat = 13.12345678
        // สมมติว่าต้องการ format ตำแหน่งทศนิยม 4 ตำแหน่ง
        val formatted = String.format("%.4f", lat)
        assertEquals("13.1235", formatted)
    }

    @Test
    fun testFormatSensorData() {
        val x = -10.5312f
        val formatted = "x: %.2f".format(x)
        assertEquals("x: -10.53", formatted)
    }

    @Test
    fun testIsValidUrl() {
        val validUrl = "https://pokeapi.co/api/v2/"
        val invalidUrl = "pokeapi.co"
        
        assertTrue(validUrl.startsWith("http"))
        assertFalse(invalidUrl.startsWith("http"))
    }

    @Test
    fun testCapitalizeFirstLetter() {
        val name = "pikachu"
        val capitalized = name.replaceFirstChar { it.uppercase() }
        assertEquals("Pikachu", capitalized)
    }
}
