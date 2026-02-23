package com.example.matikkapeli

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RulesScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Rules", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        // Simple rules text
        Text("1. Choose addition or subtraction mode.\n" +
                "2. Answer 10 random questions.\n" +
                "3. Try to get 10/10!\n\n" +
            "Onnea! \uD83C\uDF40"

        )
    }
}

@Composable
fun AboutScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        // Author and year information
        Text("Developer\uD83D\uDCBB: Maksym Kotlubaiev (@Makc)")
        Text("Year\uD83C\uDF85: 2026")
        Text("Version\uD83E\uDEE0: 1.0")
    }
}