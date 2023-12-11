package com.devapps.deutschmitarthur.data.repository

import com.devapps.deutschmitarthur.data.remote.TriviaService
import com.devapps.deutschmitarthur.domain.TriviaModels
import com.devapps.deutschmitarthur.domain.toTriviaModels

import javax.inject.Inject

class TriviaRepository @Inject constructor(
    private val triviaService: TriviaService
) {
    suspend fun getTriviaData() : List<TriviaModels> {
        return triviaService.getTriviaData().map {
            it.toTriviaModels()
        }
    }
}