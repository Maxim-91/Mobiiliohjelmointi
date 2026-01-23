package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*

class Task1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                TehtavaYks()
            }
        }
    }
}

@Composable
fun TehtavaYks() {

    // To center the Row on the screen, I use a Box
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Line where is located Text 1 and Text 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly // SpaceEvenly — equal distance between
        ) {
            // Text 1 with custom parameters
            Text(
                text = "First text",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .background(Color.Yellow)
                    .border(2.dp, Color.Black)
                    .padding(16.dp)
            )

            // Text 2 with different custom parameters
            Text(
                text = "Second text",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .border(2.dp, Color.Black)
                    .padding(16.dp)
            )
        }
    }
}

