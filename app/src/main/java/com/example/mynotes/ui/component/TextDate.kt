package com.example.mynotes.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun TextDate(dateIn: String) {
    /**val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar) **/

    /** val date = remember {
        ZonedDateTime.now()
    }

    val formattedDateTime = remember {
        DateTimeFormatter
            .ofPattern("EEEE, dd MMMM yyyy  HH:mm a")
            .format(date)
    } **/

    Text(
        text = dateIn,
        fontSize = 12.sp,
        color = MaterialTheme.colors.onPrimary
    )

}