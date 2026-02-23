package com.example.matkakululaskuri

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPerDiemClick: () -> Unit,
    onMileageClick: () -> Unit,
    onSummaryClick: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Valitse") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Matkalaskuri",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Dropdown menu for selecting calculation type
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text("Valitse") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                DropdownMenuItem(
                    text = { Text("Päivärahat") },
                    onClick = {
                        selectedOption = "Päivärahat"
                        expanded = false
                        onPerDiemClick()
                    }
                )

                DropdownMenuItem(
                    text = { Text("Matkakulut") },
                    onClick = {
                        selectedOption = "Matkakulut"
                        expanded = false
                        onMileageClick()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button to navigate to summary screen
        Button(onClick = onSummaryClick) {
            Text("Yhteenveto")
        }
    }
}