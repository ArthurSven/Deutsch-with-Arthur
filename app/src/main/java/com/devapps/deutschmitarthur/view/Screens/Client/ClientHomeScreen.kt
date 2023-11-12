package com.devapps.deutschmitarthur.view.Screens.Client

import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.devapps.deutschmitarthur.R
import com.devapps.deutschmitarthur.data.model.UserData

data class CardItem(
    val cardIcon: Int,
    val cardTitle: String,
    val cardRoute: String
)
@Composable
fun ClientHomeScreen() {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Spacer(modifier = Modifier
                .height(30.dp))
            GreetingCard()
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Get started with...",
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier
                .height(20.dp))
            CardGrid()


        }
    }
}

@Composable
fun GreetingCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .border(
                1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(4.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        val greeting by remember { mutableStateOf(greetUser()) }
        Surface(
            modifier = Modifier
                .background(color = Color.White)
                .padding(20.dp)
        ) {
            Text(text = greeting,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            )
        }

    }
}

fun greetUser() : String {
    val calendar = Calendar.getInstance()
    val timeOfTheDay = calendar.get(Calendar.HOUR_OF_DAY)

    return when (timeOfTheDay) {
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..23 -> "Good Evening"
        else -> "Hello"
    }
}

@Composable
fun CardGrid() {
    val navController = rememberNavController()
    val items = listOf(
        CardItem(
            cardIcon = R.drawable.grammar,
            cardTitle = "Grammar",
            cardRoute = "grammar_screen"
        ),
        CardItem(
            cardIcon = R.drawable.phrase,
            cardTitle = "Phrases",
            cardRoute = "phrase_screen"
        ),
        CardItem(
            cardIcon = R.drawable.quiz,
            cardTitle = "Quiz",
            cardRoute = "grammar_screen"
        ),
        CardItem(
            cardIcon = R.drawable.trivia,
            cardTitle = "Trivia",
            cardRoute = "grammar_screen"
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
           items(4) { i ->
               val cardItem = items[i]
               LazyCard(
                   selected = selectedItemIndex == i,
                   onClick = {
                       navController.navigate(cardItem.cardRoute)
                   },
                   icon = cardItem.cardIcon,
                   title = cardItem.cardTitle)
           }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyCard(
selected: Boolean,
onClick: () -> Unit,
icon: Int,
title: String
) {
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .width(140.dp)
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .height(175.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp))
            Spacer(modifier = Modifier
                .height(5.dp))
            Text(text = title,
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
@Preview
fun PreviewClientHome() {
}