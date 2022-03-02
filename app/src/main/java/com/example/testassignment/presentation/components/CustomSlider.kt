package com.example.testassignment.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.testassignment.R
import kotlin.math.roundToInt

@Composable
fun ComposeSlider() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()

    val sliderPosition = remember { mutableStateOf(1f) }
    val thumbOffSetX = remember { mutableStateOf(-(screenWidth * 1.05)) }
    val currentNumber = remember { mutableStateOf(1) }
    val showPopupThumb = remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val sliderDragged by interactionSource.collectIsDraggedAsState()
    val sliderPressed by interactionSource.collectIsPressedAsState()

    val thumbOffSetXCalculation = (((sliderPosition.value-1) * 115) - (screenWidth * 1.05))

    val thumbColor: Color
    val thumbText: String

    if (sliderDragged || sliderPressed) {
        thumbColor = colorResource(id = R.color.inactive_number)
        thumbText = ""
        showPopupThumb.value = true
    } else {
        thumbColor = colorResource(id = R.color.knob)
        thumbText = currentNumber.value.toString()
        showPopupThumb.value = false
    }

    val animatedYValue: Dp by animateDpAsState(
        targetValue = if(showPopupThumb.value) (-46).dp else 0.dp
    )

    Box(
        modifier = Modifier
            .background(
                colorResource(id = R.color.slider_background),
                shape = RoundedCornerShape(25.dp)
            )
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Row {
            for (i in 1..8) {
                CustomText(text = i.toString())
            }
        }
    }

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
        interactionSource = interactionSource,
        colors = SliderDefaults.colors(
            thumbColor = Color.Transparent,
            activeTrackColor = Color.Transparent,
            inactiveTrackColor = Color.Transparent,
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent
        )
    )

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = thumbOffSetX.value.roundToInt(),
                    y = 0
                )
            }
            .padding(start = 25.dp, end = 25.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(thumbColor),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = thumbText,
            fontSize = 21.sp,
            color = colorResource(id = R.color.active_number),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = thumbOffSetX.value.roundToInt(),
                    y = animatedYValue.roundToPx()
                )
            }
            .padding(start = 10.dp, end = 10.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(colorResource(id = R.color.popup_knob))
            .zIndex(-1f),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = currentNumber.value.toString(),
            fontSize = 21.sp,
            color = colorResource(id = R.color.active_number),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}