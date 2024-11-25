package com.cmex.fragmentsandadaptivescreen.data

import com.cmex.fragmentsandadaptivescreen.domain.Level
import com.cmex.fragmentsandadaptivescreen.domain.Numbers
import com.cmex.fragmentsandadaptivescreen.domain.Settings


interface RepositoryNumbers {
    fun settingsNumbers(level: Level): Settings
    fun generationScreen(settings: Settings) : Numbers
}