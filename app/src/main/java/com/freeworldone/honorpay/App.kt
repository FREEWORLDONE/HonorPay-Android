package com.freeworldone.honorpay

import android.app.Application
import android.content.Context

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}

lateinit var appContext: Context
    private set