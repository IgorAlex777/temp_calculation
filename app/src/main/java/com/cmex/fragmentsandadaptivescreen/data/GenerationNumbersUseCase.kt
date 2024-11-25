package com.cmex.fragmentsandadaptivescreen.data


import com.cmex.fragmentsandadaptivescreen.domain.Numbers
import com.cmex.fragmentsandadaptivescreen.domain.Settings


class GenerationNumbersUseCase(private val repositoryNumbers: RepositoryNumbers) {
    operator fun invoke(settings: Settings): Numbers {
        return  repositoryNumbers.generationScreen(settings)
    }
}