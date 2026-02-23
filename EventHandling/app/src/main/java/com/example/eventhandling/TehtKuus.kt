package com.example.eventhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.ui.theme.EventHandlingTheme

// Colors defined in the assignment
val AppPurple = Color(0xFF6200EE)
val AppYellow = Color(0xFFFFF14D)
val AppGreen = Color(0xFF00FF00)

class TehtKuus : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                MathGameApp()
            }
        }
    }
}

@Composable
fun MathGameApp() {
    var gameFinished by remember { mutableStateOf(false) }
    var finalScore by remember { mutableIntStateOf(0) }

    if (!gameFinished) {
        GameScreen(onGameFinished = { score ->
            finalScore = score
            gameFinished = true
        })
    } else {
        ResultScreen(
            score = finalScore,
            onRestart = { gameFinished = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(onGameFinished: (Int) -> Unit) {
    var questionNumber by remember { mutableIntStateOf(1) }
    var score by remember { mutableIntStateOf(0) }
    var number1 by remember { mutableIntStateOf((1..10).random()) }
    var number2 by remember { mutableIntStateOf((1..10).random()) }
    var userAnswer by remember { mutableStateOf("") }

    // For automatic focus and keyboard opening
    val focusRequester = remember { FocusRequester() }

    // Launch the keyboard immediately upon entering the screen
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        // Forces the UI to account for the keyboard height
        contentWindowInsets = WindowInsets.ime.union(WindowInsets.statusBars),
        topBar = {
            TopAppBar(
                title = { Text("Matikkapeli", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AppPurple)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Enables scrolling for small screens
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Score section
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Points", fontSize = 24.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(AppYellow),
                    contentAlignment = Alignment.Center
                ) {
                    Text(score.toString(), fontSize = 32.sp)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text("Exercise $questionNumber/5", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)

            Spacer(modifier = Modifier.height(40.dp))

            // Math problem display
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("$number1", fontSize = 24.sp)
                Text(" + ", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 8.dp))
                Text("$number2", fontSize = 24.sp)
                Text(" = ", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 8.dp))

                TextField(
                    value = userAnswer,
                    onValueChange = { userAnswer = it },
                    singleLine = true,
                    modifier = Modifier
                        .width(100.dp)
                        .background(AppGreen)
                        .focusRequester(focusRequester), // Bind the focus requester
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done // "Done" button on keyboard
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppGreen,
                        unfocusedContainerColor = AppGreen,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    val correct = number1 + number2
                    if (userAnswer.toIntOrNull() == correct) {
                        score++
                    }

                    if (questionNumber < 5) {
                        questionNumber++
                        number1 = (1..10).random()
                        number2 = (1..10).random()
                        userAnswer = ""
                        // Maintain focus for the next question
                        focusRequester.requestFocus()
                    } else {
                        onGameFinished(score)
                    }
                },
                modifier = Modifier
                    .size(150.dp, 80.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppPurple)
            ) {
                Text("SEND", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(score: Int, onRestart: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Matikkapeli", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AppPurple)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Your score was ", fontSize = 20.sp)
                Box(
                    modifier = Modifier
                        .background(AppGreen)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("$score / 5", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Text("Thank you for playing", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = onRestart,
                modifier = Modifier.size(200.dp, 80.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppPurple)
            ) {
                Text("PLAY AGAIN", fontWeight = FontWeight.Bold)
            }
        }
    }
}