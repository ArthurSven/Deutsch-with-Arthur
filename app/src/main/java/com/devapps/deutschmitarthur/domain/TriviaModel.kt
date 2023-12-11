package com.devapps.deutschmitarthur.domain

import com.devapps.deutschmitarthur.data.model.Trivia

data class TriviaModels(
    val id: String,
    val funFact: String,
    val imageUrl: String
)

fun Trivia.toTriviaModels() = TriviaModels(id, funFact, imageUrl)