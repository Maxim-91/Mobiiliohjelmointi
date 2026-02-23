package com.example.matikkapeli

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(onModeSelected: (Boolean) -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Choose math mode:", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        // Select Addition or Subtraction
        Button(onClick = { onModeSelected(true) }) { Text("Plus (+)") }
        Button(onClick = { onModeSelected(false) }) { Text("Minus (-)") }
    }
}

@Composable
fun GameScreen(isAddition: Boolean, onGameEnd: (Int) -> Unit) {
    var questionNumber by remember { mutableStateOf(1) }
    var score by remember { mutableStateOf(0) }
    var num1 by remember { mutableStateOf((1..10).random()) }
    var num2 by remember { mutableStateOf((1..10).random()) }
    var userAnswer by remember { mutableStateOf("") }

    val correctAnswer = if (isAddition) num1 + num2 else num1 - num2

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Question $questionNumber / 10")
        Spacer(Modifier.height(20.dp))
        Text("$num1 ${if (isAddition) "+" else "-"} $num2 =", style = MaterialTheme.typography.displayMedium)

        OutlinedTextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Your answer") }
        )

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                // Check answer and update score
                if (userAnswer.toIntOrNull() == correctAnswer) score++

                // Logic for 10 random questions
                if (questionNumber < 10) {
                    questionNumber++
                    num1 = (1..10).random()
                    num2 = (1..10).random()
                    userAnswer = ""
                } else {
                    onGameEnd(score)
                }
            }
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun ResultScreen(score: Int, onRestart: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Game Over!", style = MaterialTheme.typography.headlineLarge)
        Text("Your Score: $score / 10", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        // Option to restart the game
        Button(onClick = onRestart) { Text("Play Again") }
    }
}