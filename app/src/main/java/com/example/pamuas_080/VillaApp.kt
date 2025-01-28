package com.example.pamuas_080

import android.app.Application
import com.example.pamuas_080.dependenciesinjection.AppContainer
import com.example.pamuas_080.dependenciesinjection.VillaContainer

class VillaApp: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = VillaContainer()
    }
}