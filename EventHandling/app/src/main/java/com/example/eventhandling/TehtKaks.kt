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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.ui.theme.EventHandlingTheme

class TehtKaks : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                Arvonta()
            }
        }
    }
}

@Composable
fun Arvonta() {

    var num1 by remember { mutableIntStateOf(0) }
    var num2 by remember { mutableIntStateOf(0) }
    var num3 by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                //val numbers = (1..10).random()

                // Vapaaehtoinen lisätehtävä (ei lisäpisteitä, mutta pieni pähkinä):
                // arvo luvut niin, että samaan laatikkoon ei arvota kahdesti samaa numeroa

                // Tekoälyn GPT-keskustelu ehdotti .shuffled()-funktiota .random()-funktion sijaan
                val numbers = (1..10).shuffled().take(3)
                num1 = numbers[0]
                num2 = numbers[1]
                num3 = numbers[2]
            }
        ) {
            Text("Arvonta")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            NumberBox(num1)
            NumberBox(num2)
            NumberBox(num3)
        }
    }
}

@Composable
fun NumberBox(number: Int) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text =
                if(number == 0) ""
                else "$number",
            fontSize = 48.sp,
            color = Color.Black
        )
    }
}