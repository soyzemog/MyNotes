package com.example.mynotes.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid() {
    val items = listOf(
        Note(LoremIpsum(50).values.take(10).joinToString(separator = " "),
            "Subtitle 1".repeat(5),"Lunes, 10-01-2022 10:10 AM",
            true, 250, Color.Green),
        Note("Title 2", LoremIpsum(50).values.take(10).joinToString(separator = " "),
            "Martes, 4-02-2022 19:10 PM", false,140, Color.Red),
        Note(LoremIpsum(50).values.take(10).joinToString(separator = " "),
            "Subtitle 3","Jueves, 20-02-2022 02:10 AM",
            false,190, Color.Magenta),
        Note("Title 4", "Subtitle 4","Martes, 2-02-2022 10:30 AM",
            true, 120, Color.Blue),
        Note("Title 5", "Subtitle 5","Jueves, 29-02-2022 21:10 PM",
            false,120, Color.Cyan),
        Note("Title 6", "Subtitle 6","Viernes, 31-02-2022 15:15 PM",
            true,370, Color.Yellow),
        Note("Title 7", "Subtitle 7","Martes, 23-02-2022 06:10 AM",
            true,200, Color.Green),
        Note("Title 8", "Subtitle 8","Viernes, 16-02-2022 09:10 AM",
            true,160, Color.Magenta),
    )

     LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { note ->
            NoteItem(note)
        }
    }
}

data class Note(
    val title: String,
    val subtitle: String,
    val date: String,
    val image: Boolean,
    val size: Int,
    val color: Color
)