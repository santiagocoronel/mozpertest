package com.santiagocoronel.mozpertest.features.home.data.repository.local.db.tables


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EmployeeEntity")
data class EmployeeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "rating")
    val rating: Double
)