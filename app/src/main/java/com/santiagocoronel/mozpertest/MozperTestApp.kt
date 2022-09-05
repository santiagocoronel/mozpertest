package com.santiagocoronel.mozpertest

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

import com.santiagocoronel.mozpertest.network.networkModule
import com.santiagocoronel.mozpertest.preference.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.java.KoinJavaComponent.getKoin

class MozperTestApp : Application() {


    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        PreferencesManager.initialize(this)

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MozperTestApp)
            koin.loadModules(
                listOf(
                    mozperTestModule,
                    networkModule
                )
            )
        }

        getKoin().setProperty("BASE_URL", BuildConfig.BASE_URL)

    }

}