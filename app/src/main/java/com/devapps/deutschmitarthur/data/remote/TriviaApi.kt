package com.devapps.deutschmitarthur.data.remote

import com.devapps.deutschmitarthur.data.model.Trivia
import retrofit2.Response
import retrofit2.http.GET

interface TriviaApi {
    @GET("https://github.com/ArthurSven/german_with_arthur")
    suspend fun getTriviaData() : Response<List<Trivia>>
}