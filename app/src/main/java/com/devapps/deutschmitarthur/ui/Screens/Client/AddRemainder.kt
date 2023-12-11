package com.devapps.deutschmitarthur.ui.Screens.Client

import android.widget.TimePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devapps.deutschmitarthur.data.model.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRemainderScreen() {
    val applicationContext = LocalContext.current.applicationContext

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    val addReminderNavController = rememberNavController()
    NavHost(navController = addReminderNavController, startDestination = "add_remainder_screen" ){
        composable(route = "remainder_screen") {
            RemainderScreen()
        }
        composable(route = "add_remainder_screen") {
            AddRemainderScreen()
        }

    }
    var text by remember { mutableStateOf("") }

    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val timeDialogState = rememberMaterialDialogState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Spacer(modifier = Modifier
                .height(30.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp,
                text = "Add Remainder",
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier
                .height(10.dp))
            OutlinedTextField(
                value = text,
                label = { Text(text = "Add Remainder")},
                onValueChange = { text = it},
                )
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp,
                text = "Set remainder time")
            Spacer(modifier = Modifier
                .height(10.dp))
            Button(
                onClick = {
                timeDialogState.show()
            },
                colors = ButtonDefaults.buttonColors(Color.Black),
                shape = RectangleShape
            ) {
                Text(
                    text = "select time",
                    )
            }
            Spacer(modifier = Modifier
                .height(10.dp))
            OutlinedTextField(
                value = formattedTime,
                label = { Text(text = "Selected Time") },
                onValueChange = { /* do nothing since it's read-only */ },
                enabled = false // make it read-only
            )
            Spacer(modifier = Modifier
                .height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    null
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                shape = RectangleShape
            ) {
                Text(
                    text = "Set Remainder",
                )
            }
        }
        MaterialDialog(
            buttons = {
                positiveButton(text = "set") {
                    Toast.makeText(
                        applicationContext,
                        "Time set",
                        Toast.LENGTH_LONG
                    ).show()
                }
                negativeButton(text = "cancel")
            }
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Pick a time",
                timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
            ) {
                pickedTime = it
            }
        }
    }

}





@Composable
@Preview(showBackground = true)
fun ShowAddRemainder() {
    AddRemainderScreen()
}