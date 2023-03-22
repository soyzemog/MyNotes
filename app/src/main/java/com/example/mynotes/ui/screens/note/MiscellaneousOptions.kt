package com.example.mynotes.ui.screens.note

data class MiscellaneousOptions(
    val radioOptions: List<ColorPick> = listOf(
        ColorPick.GREEN,
        ColorPick.RED,
        ColorPick.YELLOW,
        ColorPick.CYAN,
        ColorPick.MAGENTA
    ),
    var selectedOption : ColorPick = radioOptions[0],
    var showModal: Boolean = false,
    var url: String = ""
)