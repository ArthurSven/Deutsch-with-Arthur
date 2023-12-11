package com.devapps.deutschmitarthur.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devapps.deutschmitarthur.domain.GetTriviaUseCase
import com.devapps.deutschmitarthur.domain.TriviaModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(private val getTriviaUseCase: GetTriviaUseCase)
    : ViewModel() {

        private val _trivia = MutableStateFlow(emptyList<TriviaModels>())
    val trivia:  StateFlow<List<TriviaModels>> get() = _trivia

    init {
        getTrivia()
    }

    private fun getTrivia() {
        viewModelScope.launch {
            try {
                val trivias = getTriviaUseCase()
                _trivia.value = trivias

            } catch (e: Exception) {
                e.printStackTrace().toString()
            }
        }
    }

}