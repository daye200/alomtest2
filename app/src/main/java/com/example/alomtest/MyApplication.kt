package com.example.alomtest

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MyApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}