package com.example.testassignment

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
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
                    CustomSlider()
                }
            }
        }
    }
}

@Composable
fun CustomSlider() {
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

        VerticalLines(numbers = listOf("1","2","3","4","5","6","7","8"))
        Thumb(color = Color.Red)
    }
}

@Composable
fun Thumb(color: Color) {
    val offSetX = remember { mutableStateOf(0f)}

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offSetX.value.roundToInt(),
                    y = 0
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offSetX.value += dragAmount.x
                }
            }
            .size(50.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "1",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Circle(color: Color) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun VerticalLines(numbers: List<String>) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(10.dp)
    ) {
        val drawPadding: Float = with(LocalDensity.current) { 20.dp.toPx() }
        Canvas(modifier = Modifier.fillMaxSize()) {
            val yStart = 0f
            val yEnd = size.height
            val distance: Float = (size.width.minus(2 * drawPadding)).div(numbers.size.minus(1))
            numbers.forEachIndexed { index, step ->
                drawLine(
                    color = Color.Red,
                    start = Offset(x = drawPadding + index.times(distance), y = yStart),
                    end = Offset(x = drawPadding + index.times(distance), y = yEnd),
                    strokeWidth = 10f
                )
            }
        }
    }
}