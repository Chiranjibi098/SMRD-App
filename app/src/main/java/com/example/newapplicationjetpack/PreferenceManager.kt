package com.example.newapplicationjetpack

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Save data
    fun save(data: String, key: String) {
        editor.putString(key, data)
        editor.apply() // Save changes asynchronously
    }

    // Get data
    fun get(key: String, defaultValue: String = ""): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    // Remove a specific key
    fun remove(key: String) {
        editor.remove(key)
        editor.apply() // Save changes asynchronously
    }

    // Clear all preferences
    fun clear() {
        editor.clear()
        editor.apply() // Save changes asynchronously
    }
}
