package com.example.lablearnandroid.utils

import android.content.Context
import android.content.SharedPreferences

// เปลี่ยนจาก class เป็น object ตรงนี้เลย (ลบ class ตัวครอบออก)
object SharedPreferencesUtil {

    private const val PREF_NAME = "my_app_prefs"
    private var sharedPreferences: SharedPreferences? = null

    /**
     * SharedPreferencesUtil: เครื่องมือช่วยจัดการการเก็บข้อมูลขนาดเล็กในเครื่อง
     * เหมาะสำหรับ: เก็บสถานะ Login, การตั้งค่า App, หรือคะแนนเกมเบื้องต้น
     */

    // ฟังก์ชันสำหรับเตรียมการใช้งาน (ต้องเรียกครั้งเดียวใน MainActivity หรือ Application)
    fun init(context: Context) {
        // ใช้ applicationContext เพื่อป้องกัน memory leak
        if (sharedPreferences == null) {
            sharedPreferences = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    // --- ฟังก์ชันสำหรับ "บันทึก" ข้อมูล (Save) ---

    fun saveString(key: String, value: String) {
        sharedPreferences?.edit()?.putString(key, value)?.apply()
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences?.edit()?.putInt(key, value)?.apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences?.edit()?.putBoolean(key, value)?.apply()
    }

    // --- ฟังก์ชันสำหรับ "ดึง" ข้อมูล (Get) ---

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences?.getString(key, defaultValue) ?: defaultValue
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences?.getInt(key, defaultValue) ?: defaultValue
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences?.getBoolean(key, defaultValue) ?: defaultValue
    }

    // --- ฟังก์ชันสำหรับ "ลบ" ข้อมูล (Delete) ---

    fun remove(key: String) {
        sharedPreferences?.edit()?.remove(key)?.apply()
    }

    fun clearAll() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}