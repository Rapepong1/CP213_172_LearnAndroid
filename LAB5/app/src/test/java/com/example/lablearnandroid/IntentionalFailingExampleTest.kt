package com.example.lablearnandroid

import org.junit.Assert.assertEquals
import org.junit.Test

class IntentionalFailingExampleTest {

    @Test
    fun expectedToFail_wrongMathResult() {
        // จำลองการเทสให้พังตามที่เห็นในรูป (Expected ..., Actual ...)
        val result = 2 + 2
        assertEquals("ผลลัพธ์การบวกเลขควรจะเป็น 5 แบบจำลองให้พัง", 5, result)
    }

    @Test
    fun expectedToFail_wrongLogic() {
        // จำลองเทสที่พังตัวที่ 2
        val input = "Hello"
        assertEquals("คำนี้ควรเป็น World", "World", input)
    }
}
