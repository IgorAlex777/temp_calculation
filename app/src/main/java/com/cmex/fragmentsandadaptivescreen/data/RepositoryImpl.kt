package com.cmex.fragmentsandadaptivescreen.data

import com.cmex.fragmentsandadaptivescreen.domain.Level
import com.cmex.fragmentsandadaptivescreen.domain.Numbers
import com.cmex.fragmentsandadaptivescreen.domain.Settings
import com.cmex.fragmentsandadaptivescreen.presentation.myLog


object RepositoryImpl :RepositoryNumbers{

    override fun settingsNumbers(level: Level): Settings {
        return when(level){
            Level.NUMBERS10-> Settings(15,1,9,25,40,6)
            Level.NUMBERS100-> Settings(15,11,99,250,400,6)
            Level.NUMBERS1000-> Settings(15,101,999,2500,4000,6)
        }
    }

    override fun generationScreen(settings: Settings): Numbers {

        val numbersList= hashSetOf<Int>()

        while (numbersList.size<settings.countNumbers){
            val number=(settings.minNumber..settings.maxNumber).random()
            numbersList.add(number)
        }
          val list=numbersList.toList().shuffled()

     return Numbers(list)
    }
}