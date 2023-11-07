package com.devapps.deutschmitarthur.view.Screens

import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
import com.devapps.deutschmitarthur.R
import com.devapps.deutschmitarthur.data.model.GoogleAuthUiClient
import com.devapps.deutschmitarthur.data.model.SignInState
import com.devapps.deutschmitarthur.viewmodel.GoogleSignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun AuthScreen() {

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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = {result ->
            if(result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    try {
                        val signInResult = googleAuthUiClient.signInWithIntent(
                            intent = result.data ?: return@launch
                        )
                        viewModel.onSignInResult(signInResult)
                    } catch (e: ApiException) {
                        // Log the error message
                        Log.e("GoogleSignInError", "Error code: ${e.statusCode}, ${e.statusMessage}")
                    }
                }
            }
        })


    Surface(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        val googleNavController = rememberNavController()
        NavHost(navController = googleNavController, startDestination = "client_home") {
            composable(route = "client_home") {
            }
        }
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

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if(state.isSignInSuccessful) {
                    Toast.makeText(context,
                        "Sign in Successful",
                        Toast.LENGTH_SHORT).show()
                }
            }
            SocialMediaSignupButton(
                onClick = {
                    coroutineScope.launch {
                        // Launch the sign-in process
                        val signInIntentSender = googleAuthUiClient.signIn()

                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                color = Color.Red,
                text = "Sign in with Google",
                iconResId = R.drawable.googs
            )


        }
    }
}

@Composable
fun SigningIn(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
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

}
