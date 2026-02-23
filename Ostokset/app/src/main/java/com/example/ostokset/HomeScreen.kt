package com.example.ostokset

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
fun HomeScreen(
    onAddPurchase: (Purchase) -> Unit,
    onSummaryClick: () -> Unit
) {

    var product by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Tuotteen tiedot",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = product,
            onValueChange = { product = it },
            label = { Text("Tuotteen nimi") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Tuotteen hinta euroissa") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Button(
                onClick = {
                    val priceValue = price.toDoubleOrNull()
                    if (product.isNotBlank() && priceValue != null) {
                        onAddPurchase(Purchase(product, priceValue))
                        product = ""
                        price = ""
                    }
                }
            ) {
                Text("Tallenna")
            }

            Button(
                onClick = onSummaryClick
            ) {
                Text("Yhteenveto")
            }
        }
    }
}