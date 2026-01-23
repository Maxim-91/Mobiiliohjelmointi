package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

class Task4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                TehtavaNel()
            }
        }
    }
}

@Composable
fun TehtavaNel() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // Button Personal Info
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(50)
            ) {
                Text(text = stringResource(R.string.personal_info))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // App title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.app_title),
                color = Color.White,
                fontSize = 32.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 6 buttons = 2 in 3 rows
        TwoButtons(
            stringResource(R.string.swimming),
            stringResource(R.string.running)
        )

        TwoButtons(
            stringResource(R.string.cycling),
            stringResource(R.string.gym)
        )

        TwoButtons(
            stringResource(R.string.skiing),
            stringResource(R.string.dancing)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Image
        Image(
            painter = painterResource(id = R.drawable.sports),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(3000.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun TwoButtons(leftText: String, rightText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActivityButton(leftText)
        ActivityButton(rightText)
    }
}

@Composable
fun ActivityButton(text: String) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(50),
        modifier = Modifier.width(140.dp)
    ) {
        Text(text)
    }
}