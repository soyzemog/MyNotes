package com.example.mynotes.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun TextDate() {
    /**val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar) **/

    val date = remember {
        ZonedDateTime.now()
    }

    val formattedDateTime = remember {
        DateTimeFormatter
            .ofPattern("EEEE, dd MMMM yyyy  HH:mm a")
            .format(date)
    }

    Text(
        text = formattedDateTime,
        fontSize = 12.sp,
        color = MaterialTheme.colors.onPrimary
    )

}