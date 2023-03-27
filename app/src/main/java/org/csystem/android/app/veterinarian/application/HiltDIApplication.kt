package org.csystem.android.app.veterinarian.application

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltDIApplication: Application() {
    companion object { //DI kullanılmazsa Application Context ve Application sınıfı için anlamlı için anlamlı
        private lateinit var mSelf: HiltDIApplication

        fun getApplication() = mSelf
    }

    override fun onCreate()
    {
        Toast.makeText(this, "Application started", Toast.LENGTH_LONG).show()
        mSelf = this
        super.onCreate()
    }
}