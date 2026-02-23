package com.example.matkakululaskuri

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PerDiemScreen(
    onSave: (Int, Double) -> Unit
) {

    var days by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Syötä päivärahat",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Input for number of travel days
        OutlinedTextField(
            value = days,
            onValueChange = { days = it },
            label = { Text("Matkapäiviä") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Input for compensation per day
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Korvaus per päivä (€)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            // Send values back to MyApp for calculation
            onSave(days.toInt(), amount.toDouble())
        }) {
            Text("Tallenna")
        }
    }
}