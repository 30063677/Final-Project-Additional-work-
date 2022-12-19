package com.umdaa.nurseasst.Objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VitalsList")
class Vitals(

    @field:ColumnInfo(name = "patient_id")
    val patientId: Int,

    @field:ColumnInfo(name = "levelconsciousness")
    var levelConsciousness: String? = null,

    @field:ColumnInfo(name = "pulserate")
    var pulseRate: String? = null,


    @field:ColumnInfo(name = "bp")
    var bp: String? = null,

    @field:ColumnInfo(name = "bp1")
    var bp1: String? = null,


    @field:ColumnInfo(name = "resprate")
    var respRate: String? = null,

    @field:ColumnInfo(name = "temperature")
    var temperature: String? = null,

    @field:ColumnInfo(name = "saturation")
    var saturation: String? = null,

    @field:ColumnInfo(name = "oxygen")
    var oxygen: String? = null,

    @field:ColumnInfo(name = "copd")
    var copd: String? = null,

    @field:ColumnInfo(name = "note")
    var note: String? = null,

    @field:ColumnInfo(name = "heart_rate_value")
    var heart_rate_value: String? = null,

    @field:ColumnInfo(name = "blood_pressure_value")
    var blood_pressure_value: String? = null,

    @field:ColumnInfo(name = "resp_rate_value")
    var resp_rate_value: String? = null,

    @field:ColumnInfo(name = "temperature_value")
    var temperature_value: String? = null,

    @field:ColumnInfo(name = "saturation_value")
    var saturation_value: String? = null,

    @field:ColumnInfo(name = "oxy_value")
    var oxy_value: Int? = null,

    @field:ColumnInfo(name = "lox_value")
    var loc_value: String? = null,

    @field:ColumnInfo(name = "priority")
    var priority: String? = null


//    @field:ColumnInfo(name = "weight")
//    var weight: String? = null,
//
//    @field:ColumnInfo(name = "height")
//    var height: String? = null
) {

    @field:PrimaryKey(autoGenerate = true)
    var VitalsId: Int? = null
}
