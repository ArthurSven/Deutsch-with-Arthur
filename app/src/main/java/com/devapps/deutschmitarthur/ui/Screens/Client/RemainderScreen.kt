package com.devapps.deutschmitarthur.ui.Screens.Client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemainderScreen() {
    val reminderNavController = rememberNavController()
    NavHost(navController = reminderNavController, startDestination = "remainder_screen" ){
        composable(route = "remainder_screen") {
            RemainderScreen()
        }
        composable(route = "add_remainder_screen") {
            AddRemainderScreen()
        }

    }
Surface(
    modifier = Modifier
        .background(Color.White)
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.Black,
                contentColor = Color.White
                , onClick = {
                    reminderNavController.navigate(route = "add_remainder_screen")
                }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }
    ) {contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Spacer(modifier = Modifier
                .height(30.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp,
                text = "Learning Schedule",
                fontWeight = FontWeight.Bold)
        }
    }

}
}

@Composable
@Preview(showBackground = true)
fun ShowScheduleScreen() {
    RemainderScreen()
}