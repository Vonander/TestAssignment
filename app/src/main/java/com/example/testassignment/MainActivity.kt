package com.example.testassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.example.testassignment.presentation.components.ComposeSlider
import com.example.testassignment.ui.theme.TestAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        *
        * Made by Johan Fornander
        * 220302
        *
        * */

        //Det blev fel, typ

        setContent {
            TestAssignmentTheme {

                val gradientBrush = Brush.linearGradient(
                    0f to colorResource(id = R.color.gradiant_start),
                    1f to colorResource(id = R.color.gradiant_end)
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBrush),
                    contentAlignment = Alignment.Center
                ) {

                    ComposeSlider()
                }
            }
        }
    }
}