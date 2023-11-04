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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import com.devapps.deutschmitarthur.R


data class MenuItem(
    val id : String,
    val title : String,
    val contentDescription: String?,

    val icon : ImageVector
)

@Composable
fun DrawerLayout() {
    Surface {
        Scaffold(
            drawer
        ) {

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
fun DrawerBody(
    items : List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Deutsch with Arthur")
    },
        modifier = Modifier
            .background(color = Color.White),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu toggle drawer"
                )
            }
        })
}

@Composable
@Preview(showBackground = true)
fun ShowMe() {
    DrawerHeader()
}