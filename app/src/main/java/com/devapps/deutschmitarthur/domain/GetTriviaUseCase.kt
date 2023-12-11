package com.devapps.deutschmitarthur.domain

import com.devapps.deutschmitarthur.data.repository.TriviaRepository
import javax.inject.Inject

class GetTriviaUseCase @Inject constructor(private val triviaRepository: TriviaRepository) {
     suspend operator fun invoke() : List<TriviaModels>  {

         return triviaRepository.getTriviaData()
     }
}