package com.example.eventhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.ui.theme.EventHandlingTheme

class TehtYks : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                Counter()
            }
        }
    }
}

@Composable
fun Counter() {

    // Counter state (saved on configuration changes)
    var counter by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 2 Buttons: Add and Decrease Visitor
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { counter++ }) {
                Text(text = "Add Visitor")
            }

            Button(
                onClick = {
                    if (counter > 0) counter--
                }
            ) {
                Text(text = "Decrease Visitor")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Label "Amount of Visitors"
        Text(
            text = "Amount of Visitors",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DarkGray box with white counter value
        Box(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(horizontal = 40.dp, vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = counter.toString(),
                color = Color.White,
                fontSize = 32.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button "Reset" "
        Button(onClick = { counter = 0 }) {
            Text(text = "Reset")
        }
    }
}
