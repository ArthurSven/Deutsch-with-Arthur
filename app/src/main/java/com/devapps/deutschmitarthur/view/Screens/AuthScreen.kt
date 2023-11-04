package com.devapps.deutschmitarthur.view.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devapps.deutschmitarthur.R

@Composable
fun AuthScreen() {
    Surface(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        val facebookBlue = Color(0xFF4267B2)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .background(color = Color.White)
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Social Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Login with one of your social network accounts",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            SocialMediaSignupButton(
                onClick = { null },
                color = facebookBlue,
                text = "Sign in with Facebook",
                iconResId = R.drawable.fb
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "- OR -",
                color = Color.LightGray,
                fontSize = 30.sp
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            SocialMediaSignupButton(
                onClick = { null },
                color = Color.Red,
                text = "Sign in with Google",
                iconResId = R.drawable.googs
            )


        }
    }
}

@Composable
fun SocialMediaSignupButton(
    onClick: () -> Unit,
    color: Color,
    text: String,
    iconResId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.White)
            .border(1.dp, color, shape = RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
    ) {
        Surface(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.width(40.dp))
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null, // Provide a meaningful description if needed
                    modifier = Modifier.size(32.dp) // Adjust the size as needed
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun AuthScreenPreview() {
    AuthScreen()
}
