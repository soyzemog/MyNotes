package com.example.mynotes.ui.screens.note

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class TitleNote(
    var title: String = "",
    val date: String = DateTimeFormatter
        .ofPattern("EEEE, dd MMMM yyyy  HH:mm a")
        .format(ZonedDateTime.now())
)