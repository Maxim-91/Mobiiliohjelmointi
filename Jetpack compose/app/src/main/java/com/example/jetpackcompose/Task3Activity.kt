package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

class Task3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                TehtavaKol()
            }
        }
    }
}

@Composable
fun TehtavaKol() {

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var other by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        // Implemented the ability to input only numbers and "+" symbol as the first if desired
        TextField(
            value = phone,
            onValueChange = { input ->
                val filtered = buildString {
                    input.forEachIndexed { index, c ->
                        when {
                            c.isDigit() -> append(c)
                            c == '+' && index == 0 -> append(c)
                        }
                    }
                }
                phone = filtered
            },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = other,
            onValueChange = { other = it },
            label = { Text("Other things (allergies etc.)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(170.dp)
                .height(150.dp)
        ) {
            Text("Save", fontSize = 36.sp)
        }
    }

}