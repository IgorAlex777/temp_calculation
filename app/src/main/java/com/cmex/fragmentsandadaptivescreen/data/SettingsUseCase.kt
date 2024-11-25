package com.cmex.fragmentsandadaptivescreen.data

import com.cmex.fragmentsandadaptivescreen.domain.Level
import com.cmex.fragmentsandadaptivescreen.domain.Settings


class SettingsUseCase (private val repository:RepositoryNumbers) {
    operator fun invoke(level: Level): Settings {
        return repository.settingsNumbers(level)
    }
}