package com.devapps.deutschmitarthur.view.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier
        .background(color = Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 30.dp)
                .background(color = Color.White)
        ) {

        }
    }
}