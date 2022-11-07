package com.example.shoppingapp

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()

    }
}