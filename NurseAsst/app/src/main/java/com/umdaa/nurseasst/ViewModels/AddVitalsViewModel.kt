package com.umdaa.nurseasst.ViewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class AddVitalsViewModel(
    application: Application,
    heart_rate_value: Int,
    bps_value: Int,
    resp_rate_value: Int,
    temps_value: Float,
    saturation_value: Int,
    o2: Int,
    loc: Int
) : AndroidViewModel(application) {


    var heart_rate: Int? = null
    var bps: Int? = null
    var resp_rate: Int? = null
    var temps: Float? = null
    var saturations: Int? = null
    var oxygen: Int? = null
    var levelOfConsciousness: Int? = null
    private var REQUEST: Int? = null


    init {
        heart_rate = heart_rate_value
        bps = bps_value
        resp_rate = resp_rate_value
        temps = temps_value
        saturations = saturation_value
        oxygen = o2
        levelOfConsciousness = loc
    }


    fun heartRateCal(): Int {
        var score: Int? = null
        if (heart_rate in 51..90) {
            score = 0
        } else if (heart_rate in 41..50 || heart_rate in 91..100) {
            score = 1
        } else if (heart_rate in 111..130) {
            score = 2
        } else if (heart_rate!! <= 40 || heart_rate!! >= 131) {
            score = 3
        }
        return score!!
    }


    fun respRateCal(): Int {
        var score: Int? = null
        if (resp_rate in 12..20) {
            score = 0
        } else if (resp_rate in 9..11) {
            score = 1
        } else if (resp_rate in 21..24) {
            score = 2
        } else if (resp_rate!! <= 8 || resp_rate!! >= 25) {
            score = 3
        }
        return score!!
    }

    fun saturationsCal(): Int {
        var score: Int? = null
        if (saturations!! >= 96) {
            score = 0
        } else if (saturations!! in 94..95) {
            score = 1
        } else if (saturations!! in 92..93) {
            score = 2
        } else if (saturations!! <= 91) {
            score = 3
        }
        return score!!
    }


    fun bloodPressureCal(): Int {
        var score: Int? = null
        if (bps in 111..140) {
            score = 0
        } else if (bps in 101..110 || bps in 141..180) {
            score = 1
        } else if (bps in 91..100 || bps in 181..220) {
            score = 2
        } else if (bps!! <= 90 || bps!! >= 221) {
            score = 3
        }
        return score!!
    }


    fun temperaureCal(): Int {
        var score: Int? = null
        if (temps!! >= 96.9 && temps!! <= 100.4) {
            score = 0
        } else if (temps!! >= 95.1 && temps!! <= 96.8 || temps!! >= 100.5 && temps!! <= 102.2) {
            score = 1
        } else if (temps!! > 102.2) {
            score = 2
        } else if (temps!! <= 95.0) {
            score = 3
        }
        return score!!
    }


    fun levelOfConsciousnessCal(): Int {
        var score: Int? = null
        if (levelOfConsciousness == 0) {
            score = 0
        } else if (levelOfConsciousness == 1) {
            score = 3
        } else if (levelOfConsciousness == 2) {
            score = 3
        } else if (levelOfConsciousness == 3) {
            score = 3
        } else if (levelOfConsciousness == 101) {
            score = 101
        }
        return score!!
    }


    fun oxygenMaskCal(): Int {

        var score: Int? = null

        if (oxygen == 0) {
            score = 0
        } else if (oxygen == 1) {
            score = 1
        } else if (oxygen == 101) {
            score = 101
        }
        return score!!
    }


    var list = arrayListOf(
        levelOfConsciousnessCal()!!,
        heartRateCal()!!,
        bloodPressureCal()!!,
        respRateCal()!!,
        temperaureCal()!!,
        saturationsCal()!!,
        oxygenMaskCal()!!
    )

    val total_score = levelOfConsciousnessCal()!! +
            heartRateCal()!! +
            bloodPressureCal()!! +
            respRateCal()!! +
            temperaureCal()!! +
            saturationsCal()!! +
            oxygenMaskCal()!!


    fun finalScore(): Int {

        for (i in 0..list.size - 1) {

            if (list[i] == 3) {
                REQUEST = 100
            }
        }
        var final_score: Int? = null
        if (total_score in 0..4 && REQUEST == 100) {
            final_score = 1
        } else if (total_score in 5..6) {
            final_score = 1
        } else if (total_score >= 7) {
            final_score = 2
        } else if (total_score in 0..4) {
            final_score = 0
        }
        return final_score!!
    }

}