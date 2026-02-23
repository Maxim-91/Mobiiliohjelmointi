package com.example.ostokset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SummaryScreen(
    purchases: List<Purchase>,
    onReset: () -> Unit
) {

    val total = purchases.sumOf { it.price }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Ostokset",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        purchases.forEach {
            Text("${it.name}  ${it.price} €")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .background(Color.Green)
                .padding(12.dp)
        ) {
            Text(
                text = "Yhteensä $total €",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onReset
        ) {
            Text("Aloita alusta")
        }
    }
}