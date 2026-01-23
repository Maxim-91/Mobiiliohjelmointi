package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

class Task2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                    TehtavaKaks()
                }
            }
        }
    }

@Composable
fun TehtavaKaks() {
    // I divide the screen into 3 blocks/frames (in Column): top, center, bottom
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Frame 1 - Top
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(2.dp, Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SimpleBox("BOX 1", Color.White, Color.DarkGray, 80.dp, 20.sp)
                SimpleBox("BOX 2", Color.Black, Color.Cyan, 80.dp, 20.sp)
            }
        }

        // Frame 2 - Center
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(2.dp, Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SimpleBox("BOX 3", Color.Black, Color.Yellow, 40.dp, 10.sp)
                SimpleBox("BOX 4", Color.White, Color.Green, 40.dp, 10.sp)
                SimpleBox("BOX 5", Color.White, Color.Magenta, 40.dp, 10.sp)
                SimpleBox("BOX 6", Color.White, Color.Blue, 40.dp, 10.sp)
            }
        }

        // Frame 3 - Bottom
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(2.dp, Color(0xFFFF69B4)), // pink
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SimpleBox("BOX 7", Color.White, Color.DarkGray, 80.dp, 20.sp)
                SimpleBox("BOX 8", Color.Black, Color.Cyan, 80.dp, 20.sp)
            }
        }
    }
}

@Composable
fun SimpleBox(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    size: Dp,
    fontSize: TextUnit
) {
    Box(
        modifier = Modifier
            .size(size) // changing box size
            .background(backgroundColor) // changing background color
            .border(1.dp, Color.Black), // fixed black border
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, // changing text/name
            color = textColor, // changing text color
            fontSize = fontSize // changing text size
        )
    }
}


