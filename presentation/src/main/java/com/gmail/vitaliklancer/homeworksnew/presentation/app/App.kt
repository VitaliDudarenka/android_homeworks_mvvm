package com.gmail.vitaliklancer.homeworksnew.presentation.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric



class App : Application() {
    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
    }
}