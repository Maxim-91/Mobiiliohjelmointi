package com.example.eventhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.ui.theme.EventHandlingTheme

class TehtViis : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                DiceGameApp()
            }
        }
    }
}

/* List containing all dice images
   Index 0 = empty dice */
val dices = listOf(
    R.drawable.empty_dice,
    R.drawable.dice_1,
    R.drawable.dice_2,
    R.drawable.dice_3,
    R.drawable.dice_4,
    R.drawable.dice_5,
    R.drawable.dice_6
)

@Composable
fun DiceGameApp() {

    // Current dice values
    var dice1 by remember { mutableStateOf(0) }
    var dice2 by remember { mutableStateOf(0) }

    // Total score
    var score by remember { mutableStateOf(0) }

    // How many throws have been used
    var throwsCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Pisteet: $score", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(24.dp))

        // Row containing two dice images
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            DiceImage(dice1)
            DiceImage(dice2)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Throw button (disabled after 3 throws)
        Button(
            onClick = {

                // Generate random numbers 1–6
                dice1 = (1..6).random()
                dice2 = (1..6).random()

                // Calculate score
                if (dice1 == dice2) {
                    // Same numbers → sum of both dice
                    score += dice1 + dice2
                } else {
                    // Otherwise → larger value
                    score += maxOf(dice1, dice2)
                }

                throwsCount++
            },
            enabled = throwsCount < 3
        ) {
            Text("Heitä")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Start button (enabled after 3 throws)
        Button(
            onClick = {
                // Reset the game
                dice1 = 0
                dice2 = 0
                score = 0
                throwsCount = 0
            },
            enabled = throwsCount == 3
        ) {
            Text("Aloita")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Käytetyt heitot: $throwsCount / 3")
    }
}

@Composable
fun DiceImage(diceValue: Int) {
    Image(
        painter = painterResource(id = dices[diceValue]),
        contentDescription = "Dice value",
        modifier = Modifier.size(120.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDiceGame() {
    EventHandlingTheme {
        DiceGameApp()
    }
}