package com.santiagocoronel.mozpertest.features.home.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.dao.EmployeeEntityDao
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.tables.EmployeeEntity

@Database(
    entities = [EmployeeEntity::class],
    version = 1
)
abstract class MozperDataBase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeEntityDao


}