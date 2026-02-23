package com.example.matkakululaskuri

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SummaryScreen(
    perDiemTotal: Double,
    mileageTotal: Double,
    onContinue: () -> Unit,
    onReset: () -> Unit
) {

    val total = perDiemTotal + mileageTotal

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Yhteenveto kuluista",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Show calculated expense categories
        Text("Päivärahat: %.1f €".format(perDiemTotal))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Kilometrit: %.1f €".format(mileageTotal))

        Spacer(modifier = Modifier.height(16.dp))

        // Highlight total cost
        Box(
            modifier = Modifier
                .background(Color.Green)
                .padding(12.dp)
        ) {
            Text(
                text = "Yhteensä: %.1f €".format(total),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onContinue) {
            Text("Jatka")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onReset) {
            Text("Aloita alusta")
        }
    }
}