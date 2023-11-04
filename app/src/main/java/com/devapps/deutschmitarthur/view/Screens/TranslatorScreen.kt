package com.devapps.deutschmitarthur.view.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.Dp
import com.devapps.deutschmitarthur.R


@Composable
fun TranslatorScreen() {
    val facebookBlue = Color(0xFF4267B2)
    Surface(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = "English - German Translator",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Input",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        LanguageIcon(
                            languageImage = R.drawable.eng,
                            contentDescription = null)
                        Spacer(modifier = Modifier
                            .width(20.dp))
                        Text(text = "English",
                            fontSize = 24.sp)
                    }
                    Spacer(modifier = Modifier
                        .height(20.dp))
                }
            }
        }
    }

}

@Composable
fun LanguageIcon(
    languageImage: Int,
    contentDescription: String?,
    imageSize: Dp = 40.dp, // Size in dp
) {
    Image(
        painter = painterResource(id = languageImage),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
            .aspectRatio(1f),
                contentScale = ContentScale.Crop)

}

@Composable
@Preview(showBackground = true)
fun TranslatorPreview() {
    TranslatorScreen()
}