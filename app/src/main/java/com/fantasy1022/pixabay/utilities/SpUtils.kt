package com.fantasy1022.pixabay.utilities

import android.content.Context
import android.content.SharedPreferences

class SpUtils(context:Context) {
    private val sp: SharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    init {
        editor = sp.edit()
        editor.apply()
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sp.getInt(key, defaultValue)
    }

}