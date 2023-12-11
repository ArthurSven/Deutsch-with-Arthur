package com.devapps.deutschmitarthur.data.remote

import com.devapps.deutschmitarthur.data.model.Trivia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TriviaService @Inject constructor(private val triviaApi: TriviaApi) {

    suspend fun getTriviaData() : List<Trivia> {
         return withContext(Dispatchers.IO) {
             val trivias = triviaApi.getTriviaData()
             trivias.body()?: emptyList()
         }
    }
}