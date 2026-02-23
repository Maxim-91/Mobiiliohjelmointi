package com.example.eventhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.ui.theme.EventHandlingTheme

class TehtNel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                BasketballApp()
            }
        }
    }
}

@Composable
fun BasketballApp() {

    // Score states
    var homeScore by remember { mutableStateOf(0) }
    var awayScore by remember { mutableStateOf(0) }

    // Foul states
    var homeFouls by remember { mutableStateOf(0) }
    var awayFouls by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Top row showing fouls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FoulIndicator("Virheet", homeFouls)
            FoulIndicator("Virheet", awayFouls)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Team names
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("KOTI", fontSize = 20.sp)
            Text("VIERAS", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Score display
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ScoreBox(homeScore)
            ScoreBox(awayScore)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Buttons section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // Home team buttons
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ScoreButtons(
                    onAdd1 = { homeScore += 1 },
                    onAdd2 = { homeScore += 2 },
                    onAdd3 = { homeScore += 3 },
                    onFoul = { homeFouls++ }
                )
            }

            // Away team buttons
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ScoreButtons(
                    onAdd1 = { awayScore += 1 },
                    onAdd2 = { awayScore += 2 },
                    onAdd3 = { awayScore += 3 },
                    onFoul = { awayFouls++ }
                )
            }
        }
    }
}

@Composable
fun ScoreBox(score: Int) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$score",
            fontSize = 28.sp,
            color = Color.Black
        )
    }
}

@Composable
fun FoulIndicator(label: String, fouls: Int) {

    // Background changes if fouls > 3
    val backgroundColor =
        if (fouls > 3) Color.Red
        else Color.Green

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(label, fontSize = 16.sp)

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(30.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$fouls",
                color = Color.Black
            )
        }
    }
}

@Composable
fun ScoreButtons(
    onAdd1: () -> Unit,
    onAdd2: () -> Unit,
    onAdd3: () -> Unit,
    onFoul: () -> Unit
) {

    Button(onClick = onAdd1, modifier = Modifier.padding(4.dp)) {
        Text("+1p")
    }

    Button(onClick = onAdd2, modifier = Modifier.padding(4.dp)) {
        Text("+2p")
    }

    Button(onClick = onAdd3, modifier = Modifier.padding(4.dp)) {
        Text("+3p")
    }

    Button(onClick = onFoul, modifier = Modifier.padding(4.dp)) {
        Text("Virhe")
    }
}