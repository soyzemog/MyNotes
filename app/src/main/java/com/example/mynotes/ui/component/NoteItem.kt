package com.example.mynotes.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotes.R
import com.example.mynotes.data.domain.Note

@Composable
fun NoteItem(note: Note) {

    Card(
        modifier = Modifier.clickable { },
        shape = MaterialTheme.shapes.large,
        backgroundColor = note.color!!.color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (note.image != null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "image",
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        //.height(note.size!!.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 6.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = note.title.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = note.subtitle.toString(),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = note.date.toString(),
                    fontSize = 10.sp,
                    maxLines = 2
                )
            }
        }
    }
}