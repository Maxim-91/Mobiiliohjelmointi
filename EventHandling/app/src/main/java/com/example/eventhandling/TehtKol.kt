package com.example.eventhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.ui.theme.EventHandlingTheme

class TehtKol : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                PhotoViewerApp()
            }
        }
    }
}

// List of image resources
val imageList = listOf(
    R.drawable.aurora,
    R.drawable.beach1,
    R.drawable.beach2,
    R.drawable.church,
    R.drawable.motorbike
)

@Composable
fun PhotoViewerApp() {

    // Current image index
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Image container (takes most of the screen)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // takes available vertical space
            contentAlignment = Alignment.TopCenter
        ) {

            // Display current image
            Image(
                painter = painterResource(id = imageList[currentIndex]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Text showing current position (e.g. 1/5)
            Text(
                text = "${currentIndex + 1}/${imageList.size}",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(
                        Color.Black.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Previous button
            Button(
                onClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                    } else {
                        currentIndex = imageList.lastIndex // go to last image
                    }
                }
            ) {
                Text("Previous")
            }

            // Next button
            Button(
                onClick = {
                    if (currentIndex < imageList.lastIndex) {
                        currentIndex++
                    } else {
                        currentIndex = 0 // return to first image
                    }
                }
            ) {
                Text("Next")
            }
        }
    }
}