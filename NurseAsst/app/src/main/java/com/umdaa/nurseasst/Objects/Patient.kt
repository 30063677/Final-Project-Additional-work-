package com.umdaa.nurseasst.Objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Patients")
class Patient(

    @SerializedName("patient_name")
    @field:PrimaryKey
    val id: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @SerializedName("patient_gender")
    @field:ColumnInfo(name = "gender")
    val gender: String,

    @SerializedName("patient_age")
    @field:ColumnInfo(name = "age")
    val age: String,

    @SerializedName("patient_mobile_number")
    @field:ColumnInfo(name = "mobile")
    val mobile: String,

    @SerializedName("patient_id_number")
    @field:ColumnInfo(name = "aadharId")
    val aadharId: String,


    @SerializedName("patient_location")
    @field:ColumnInfo(name = "location")
    val location: String,

    @field:ColumnInfo(name = "sync_status")
    val syncStatus: Boolean


)