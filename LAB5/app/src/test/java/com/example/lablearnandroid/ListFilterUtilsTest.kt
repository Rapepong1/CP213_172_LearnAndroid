package com.example.lablearnandroid

import org.junit.Assert.assertEquals
import org.junit.Test

class ListFilterUtilsTest {

    @Test
    fun testEmptyListReturnsEmpty() {
        val list = emptyList<String>()
        val filtered = list.filter { it.length > 5 }
        assertEquals(0, filtered.size)
    }

    @Test
    fun testFilterOutShortStrings() {
        val list = listOf("Bulbasaur", "Mew", "Pikachu", "Abra")
        val filtered = list.filter { it.length > 4 }
        
        assertEquals(2, filtered.size)
        assertEquals("Bulbasaur", filtered[0])
        assertEquals("Pikachu", filtered[1])
    }

    @Test
    fun testFindExistingElement() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.find { it == 3 }
        
        assertEquals(3, result)
    }

    @Test
    fun testFindMissingElementReturnsNull() {
        val list = listOf(1, 2, 4, 5)
        val result = list.find { it == 3 }
        
        assertEquals(null, result)
    }
}
