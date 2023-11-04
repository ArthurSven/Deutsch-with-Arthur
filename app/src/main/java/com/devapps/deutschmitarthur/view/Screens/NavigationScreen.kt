package com.devapps.deutschmitarthur.view.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devapps.deutschmitarthur.R

data class BottomNavigation(
    val title: String,
    val selected: ImageVector,
    val unSelected: ImageVector,
    val route: String

)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavigation(
            title = "Home",
            selected = Icons.Filled.Home,
            unSelected = Icons.Outlined.Home,
            route = "home_screen"
        ),
        BottomNavigation(
            title = "Account",
            selected = Icons.Filled.AccountCircle,
            unSelected = Icons.Outlined.AccountCircle,
            route = "auth_screen"
        ),
        BottomNavigation(
            title = "Translator",
            selected = Icons.Filled.List,
            unSelected = Icons.Outlined.List,
            route = "translator_screen"
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
Surface(
    modifier = Modifier
        .background(color = Color.White)
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                                  selectedItemIndex = index
                            navController.navigate(item.route)
                        },
                        label = {
                                Text(text = item.title)
                        },
                        icon = { 
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                                  item.selected
                                } else item.unSelected, contentDescription = item.title
                            )
                        })
                }
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            // Define your routes in the NavHost
            NavHost(navController = navController, startDestination = "home_screen") {
                composable(route = "home_screen") {
                    HomeScreen()
                }
                composable(route = "auth_screen") {
                    AuthScreen()
                }
                composable(route = "translator_screen") {
                    TranslatorScreen()
                }
            }
        }
        }
    }

}




@Composable
@Preview(showBackground = true)
fun NavPreview() {

}

