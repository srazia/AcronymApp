package com.android.acronymsearchapp.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AcronymApplication : MultiDexApplication(){

    init {
        instance = this
    }

    companion object {

        private var instance: AcronymApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

    }
}