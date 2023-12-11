package com.devapps.deutschmitarthur.ui.Screens.Client

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.devapps.deutschmitarthur.data.model.GoogleAuthUiClient
import com.devapps.deutschmitarthur.domain.TriviaModels
import com.devapps.deutschmitarthur.ui.viewmodel.GoogleSignInViewModel
import com.devapps.deutschmitarthur.ui.viewmodel.TriviaViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaScreen() {
    val triviaNavController = rememberNavController()

    val context = LocalContext.current.applicationContext

    val coroutineScope = rememberCoroutineScope()
    val viewModel = viewModel<GoogleSignInViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    NavHost(navController = triviaNavController, startDestination = "trivia_screen") {
        composable(route = "trivia_screen") {
            Trivia()
        }
        composable(route = "drawer_screen") {
            DrawerLayout(userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    coroutineScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()
                        triviaNavController.popBackStack()
                    }
                })
        }
    }


    Surface {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    Text(text = "Trivia!")
                },
                    navigationIcon = {
                        IconButton(onClick = {
                            triviaNavController.navigate(route = "drawer_screen")
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "cion back to home screen")
                        }
                    })
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Trivia()
            }
        }
    }
}

@Composable
fun Trivia() {
    val triviaViewModel =  viewModel(modelClass = TriviaViewModel::class.java)
    val trivias by triviaViewModel.trivia.collectAsState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Spacer(modifier = Modifier
                .height(30.dp))
            Text(text = "Fun facts about German and the German language",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth())
            Spacer(modifier = Modifier
                .height(10.dp))
            LazyColumn {
                items(trivias) {trivs: TriviaModels ->
                    TriviaCard(trivia = trivs)
                }
            }
        }
    }
}

@Composable
fun TriviaCard(trivia: TriviaModels) {

    val triviaImage = rememberAsyncImagePainter(model = trivia.imageUrl)
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(painter = triviaImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Text(text = trivia.funFact,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,)
    }
}

@Composable
@Preview(showBackground = true)
fun ViewScreens() {
    TriviaScreen()
}