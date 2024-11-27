package com.cmex.fragmentsandadaptivescreen.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val flagResult:Boolean,
    val sumNumbersMin: Int,
    val sumNumbersMax:Int
):Parcelable{
    val minSumString:String
        get() = "$sumNumbersMin-min Sum"
    val maxSumString:String
        get() = "$sumNumbersMax-max Sum"
}
