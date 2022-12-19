package com.umdaa.nurseasst.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ValidationViewModel(
    application: Application,
    hr1: String, bp1: String,
    rr1: String,
    temp1: String,
    sat1: String
) : AndroidViewModel(application) {


    var value: String? = null
    var bp: String? = null
    var rr: String? = null
    var temp: String? = null
    var sat: String? = null


    init {
        value = hr1
        bp = bp1
        rr = rr1
        temp = temp1
        sat = sat1
    }

    fun heart_rates(): String {

        var score: String? = null


        if (value!!.toInt() > 500 || value!!.toInt() < 0) {
            score = "Invalid Heart Rate Readings Please check"
        } else if (value!!.toInt() == null) {
            score = "Please fill Heart Rate"
        } else if (value!!.toInt() <= 500 || value!!.toInt() >= 0 && value!!.isNotEmpty()) {
            score = "null"
        }
        return score!!
    }

    fun bloodPressures(): String {
        var score: String? = null
        if (bp!!.toInt() > 300 || bp!!.toInt() < 0) {
            score = "Invalid Blood Preassure Readings Please check"
        } else if (bp!!.toInt() == null) {
            score = "Please fill  Blood Pressure"
        } else {
            score = "null"
        }
        return score!!
    }

    fun respRates(): String {
        var score: String? = null
        if (rr!!.toInt() > 80 || rr!!.toInt() < 0) {
            score = "Invalid Resp Rate Readings Please check"
        } else if (rr!!.toInt() == null) {
            score = "Please fill  Resp rate"
        } else {
            score = "null"
        }
        return score!!
    }

    fun saturationss(): String {
        var score: String? = null
        if (sat!!.toInt() > 100 || sat!!.toInt() < 0) {
            score = "Invalid Saturation Readings Please check"
        } else if (sat!!.toInt() == null) {
            score = "Please fill  Resp rate"
        } else {
            score = "null"
        }
        return score!!
    }

    fun temperaureCalucation(): String {
        var score: String? = null
        if (temp!!.toFloat() > 150 || temp!!.toFloat() < 60) {
            score = "Invalid Temperature Readings Please check"
        } else if (temp!!.toFloat() == null) {
            score = "Please fill  Resp rate"
        } else {
            score = "null"
        }
        return score!!
    }
}