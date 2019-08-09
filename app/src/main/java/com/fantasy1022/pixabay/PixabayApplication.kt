package com.fantasy1022.pixabay

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class PixabayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}