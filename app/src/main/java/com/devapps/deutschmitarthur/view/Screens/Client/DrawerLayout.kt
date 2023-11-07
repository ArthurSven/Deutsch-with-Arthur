package com.devapps.deutschmitarthur.view.Screens.Client

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devapps.deutschmitarthur.R
import kotlinx.coroutines.launch


data class MenuItem(
    val title : String,
    val contentDescription: String?,
    val icon : ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerLayout() {
    val items = listOf(
MenuItem(
    title = "Home",
    contentDescription = "Home",
    icon = Icons.Default.Home,
    route = "client_home"
),
        MenuItem(
            title = "Schedule",
            contentDescription = "Learning Schedule",
            icon = Icons.Default.Check,
            route = "schedule_screen"
        ),
        MenuItem(
            title = "Logout",
            contentDescription = "Logout",
            icon = Icons.Default.ArrowForward,
            route = "logout"
        )
    )
    Surface {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    DrawerHeader()
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                    Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title)
                            })
                    }
                }
            },
            drawerState = drawerState) {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = "Deutsch with Arthur")
                    },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Side menu toggle")
                            }
                        })
                }
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)) {
                        val clientNavController = rememberNavController()
                    NavHost(navController = clientNavController, startDestination = "clienthome_screen") {
                        composable(route = "clienthome_screen") {
                            ClientHomeScreen()
                        }
                        composable(route = "schedule_screen") {
                            ScheduleScreen()
                        }
                        composable(route = "logout") {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
            .padding(vertical = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            ProfileIcon(profileImage = R.drawable.ger, contentDescription = null)
        Spacer(modifier = Modifier
            .height(5.dp))
        Text(text = "Deutschland",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)
    }
}


@Composable
fun ProfileIcon(
    profileImage: Int,
    contentDescription: String?,
    imageSize: Dp = 100.dp, // Size in dp
) {
    Image(
        painter = painterResource(id = profileImage),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
            .border(3.dp, Color.Gray, CircleShape)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop)
}


@Composable
@Preview(showBackground = true)
fun ShowMe() {
    DrawerHeader()
}