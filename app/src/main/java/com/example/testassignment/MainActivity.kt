package com.example.testassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testassignment.ui.theme.TestAssignmentTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAssignmentTheme {

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.LightGray
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    ComposeSlider()
                }
            }
        }
    }
}

@Composable
fun ComposeSlider() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()

    val sliderPosition = remember { mutableStateOf(0f) }
    val thumbOffSetX = remember { mutableStateOf(-(screenWidth * 1.05))}
    val currentNumber = remember { mutableStateOf(1) }

    val interactionSource = remember { MutableInteractionSource() }
    val sliderDragged by interactionSource.collectIsDraggedAsState()
    val sliderPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .background(
                Color.DarkGray,
                shape = RoundedCornerShape(30.dp)
            )
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Row {
            for (i in 1..8) {
                CustomText(text = i.toString())
            }
        }
    }

    val thumbOffSetXCalculation = ((((sliderPosition.value-1) * 115) - (screenWidth * 1.05)))

    Slider(
        value = sliderPosition.value,
        valueRange = 1f..8f,
        steps = 6,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
        ,
        onValueChange = {
            sliderPosition.value = it
            thumbOffSetX.value = thumbOffSetXCalculation
            currentNumber.value = sliderPosition.value.toInt()
        },
        onValueChangeFinished = {
            println("okej onValueChangeFinished!")
        },
        interactionSource = interactionSource
    )

    val thumbColor = if (sliderDragged || sliderPressed) {
        Color.LightGray
    } else {
        Color.Yellow
    }

    val thumbText = if (sliderDragged || sliderPressed) {
        ""
    } else {
        currentNumber.value.toString()
    }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = thumbOffSetX.value.roundToInt(),
                    y = 0
                )
            }
            .padding(start = 25.dp, end = 25.dp)
            .size(50.dp)
            .clip(CircleShape)
            .background(thumbColor),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = thumbText,
            fontSize = 40.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = thumbOffSetX.value.roundToInt(),
                    y = -155
                )
            }
            .padding(start = 25.dp, end = 25.dp)
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = currentNumber.value.toString(),
            fontSize = 40.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        fontSize = 40.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 9.dp, end = 9.dp)
    )
}