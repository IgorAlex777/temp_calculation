package com.cmex.fragmentsandadaptivescreen.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val flagResult:Boolean,
    val sumNumbersMin: Int,
    val sumNumbersMax:Int
):Parcelable
