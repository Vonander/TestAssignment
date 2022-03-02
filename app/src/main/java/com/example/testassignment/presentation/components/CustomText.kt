package com.example.testassignment.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testassignment.R

@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        fontSize = 21.sp,
        color = colorResource(id = R.color.inactive_number),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
    )
}