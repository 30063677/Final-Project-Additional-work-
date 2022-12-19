package com.umdaa.nurseasst.Ulits

import android.app.Application
import android.text.InputFilter
import android.text.Spanned
import androidx.lifecycle.AndroidViewModel

class InputFilterMinMax: InputFilter {



    private var mIntMin: Double = 0.0
    private var mIntMax: Double = 0.0

    constructor(minValue: Double, maxValue: Double) {
        this.mIntMin = minValue
        this.mIntMax = maxValue
    }

    constructor(minValue: String, maxValue: String) {
        this.mIntMin = java.lang.Double.parseDouble(minValue)
        this.mIntMax = java.lang.Double.parseDouble(maxValue)
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        try {
            val input = java.lang.Double.parseDouble(dest.toString() + source.toString())
            if (isInRange(mIntMin, mIntMax, input))
                return null
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }
}