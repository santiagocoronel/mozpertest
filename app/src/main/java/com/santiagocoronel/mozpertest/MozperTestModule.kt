package com.santiagocoronel.mozpertest

import android.content.Context
import androidx.room.Room
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.MozperDataBase
import org.koin.core.module.Module
import org.koin.dsl.module

val mozperTestModule: Module = module {

    single { provideMozperTestDataBase(get()) }

}

fun provideMozperTestDataBase(applicationContext: Context): MozperDataBase {
    return Room.databaseBuilder(applicationContext, MozperDataBase::class.java, "MozperDataBase")
        .build()
}