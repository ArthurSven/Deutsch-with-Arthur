package com.devapps.deutschmitarthur.ui.Screens.Client

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.ImageResult
import com.devapps.deutschmitarthur.data.model.GoogleAuthUiClient
import com.devapps.deutschmitarthur.data.model.UserData
import com.devapps.deutschmitarthur.ui.Screens.HomeScreen
import com.devapps.deutschmitarthur.ui.viewmodel.GoogleSignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch



data class MenuItem(
    val title : String,
    val contentDescription: String?,
    val icon : ImageVector,
    val route : String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerLayout(
    userData: UserData?,
    onSignOut: () -> Unit) {

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

    val clientNavController = rememberNavController()

    Surface {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .requiredWidth(300.dp)
                ) {
                        DrawerHeader(userData)
                        DrawerBody(clientNavController)
                }
            },
            drawerState = drawerState) {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(text = "Deutsch with Arthur")
                    },
                        Modifier.background(color = Color.White),
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
                    NavHost(navController = clientNavController, startDestination = "clienthome_screen") {
                        composable(route = "clienthome_screen") {
                            ClientHomeScreen()
                        }
                        composable(route = "remainder_screen") {
                            RemainderScreen()
                        }
                        composable(route = "logout_user") {
                            LaunchedEffect(Unit) {
                                onSignOut() // Perform sign-out
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DrawerHeader(userData: UserData?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(vertical = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(userData?.profilePictureUrl != null && userData?.username != null) {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(userData.profilePictureUrl)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .allowHardware(false)
                .build()
            AsyncImage(
                model = request,
                contentDescription = "${userData.username}'s profile picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier
                .height(5.dp))
            Text(text = userData.username.toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
        } else {
            Text(text = userData?.username.toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(drawerController: NavHostController) {
    val items = listOf(
        MenuItem(
            title = "Home",
            contentDescription = "Home",
            icon = Icons.Default.Home,
            route = "clienthome_screen"
        ),
        MenuItem(
            title = "Remainder",
            contentDescription = "Remainder",
            icon = Icons.Default.Notifications,
            route = "remainder_screen"
        ),
        MenuItem(
            title = "Logout",
            contentDescription = "Logout",
            icon = Icons.Default.ArrowForward,
            route = "logout_user"
        )
    )

    var selectedItem by remember {
        mutableStateOf(items[0])
    }

    items.forEach { item ->
        NavigationDrawerItem(label = {
            Text(text = item.title)
        },
            selected = item == selectedItem,
            onClick = {
                selectedItem = item
                drawerController.navigate(item.route)
            },
            icon = {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription)
            },
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding))
    }

}





@Composable
@Preview(showBackground = true)
fun ShowMe() {

}