package com.alicja.sklepallegro.helper

import java.text.DecimalFormat

object Round {
    fun roundTo2Decimals(number : Double) : String {
        val df = DecimalFormat("0.00")
        return df.format(number)
    }
}