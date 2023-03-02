package com.example.mynotes.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import java.text.DateFormat
import java.util.*

@Composable
fun TextDate() {
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)

    Text(
        text = "$dateFormat",
        fontSize = 12.sp,
        color = Color.LightGray
    )

}