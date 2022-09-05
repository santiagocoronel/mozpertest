package com.santiagocoronel.mozpertest

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.messaging.FirebaseMessaging
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.MozperDataBase
import com.santiagocoronel.mozpertest.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.java.KoinJavaComponent.getKoin

class MozperTestApp : Application() {


    override fun onCreate() {
        super.onCreate()

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