package com.cmex.fragmentsandadaptivescreen.data

import com.cmex.fragmentsandadaptivescreen.domain.Level
import com.cmex.fragmentsandadaptivescreen.domain.Numbers
import com.cmex.fragmentsandadaptivescreen.domain.Settings
import com.cmex.fragmentsandadaptivescreen.presentation.myLog


object RepositoryImpl :RepositoryNumbers{

    override fun settingsNumbers(level: Level): Settings {
        return when(level){
            Level.NUMBERS10-> Settings(5,1,9,25,35,6)
            Level.NUMBERS100-> Settings(15,11,99,250,300,6)
            Level.NUMBERS1000-> Settings(5,101,999,2500,3000,6)
        }
    }

    override fun generationScreen(settings: Settings): Numbers {
        var sumMin= 0
        var sumMax=0
        val numbersList= hashSetOf<Int>()
        myLog("NUMBERLIST_0=$numbersList")
        while (numbersList.size<settings.countNumbers){
            val number=(settings.minNumber..settings.maxNumber).random()
            numbersList.add(number)
        }
        myLog("NUMBERLIST_1=$numbersList")
        val sum=numbersList.sum()
      if(sum<=settings.minSum){
          sumMin=sum
        }else if(sum>=settings.maxSum){
            sumMax=sum
        }
     return Numbers(sumMin,sumMax,numbersList.toList())
    }
}