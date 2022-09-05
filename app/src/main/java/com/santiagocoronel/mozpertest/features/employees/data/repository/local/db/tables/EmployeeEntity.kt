package com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables


import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(firstName)
        parcel.writeString(image)
        parcel.writeString(lastName)
        parcel.writeDouble(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeeEntity> {
        override fun createFromParcel(parcel: Parcel): EmployeeEntity {
            return EmployeeEntity(parcel)
        }

        override fun newArray(size: Int): Array<EmployeeEntity?> {
            return arrayOfNulls(size)
        }
    }
}