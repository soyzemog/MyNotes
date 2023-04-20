package com.example.mynotes.ui.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    onClick: () -> Unit,
    saveNote: () -> Unit
) {
    TopAppBar(
        title = {
            /** if (appState.showTitle) {
                Text(
                    text = "New Note",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            } **/
            Text(
                text = "New Note",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            /** if (appState.showUpNavigation) {
                IconButton(
                    onClick = { appState.onUpClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "Go to previous Screen",
                        tint = Color.White
                    )
                }
            } **/
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "Go to previous Screen",
                    tint = Color.White
                )
            }
        },
        actions = {
            /** if (appState.showAction) {
                IconButton(
                    onClick = {
                        viewModel.saveNote()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.TaskAlt,
                        contentDescription = "arrow confirm",
                        tint = Color.White
                    )
                }
            } **/
            IconButton(
                onClick = saveNote
            ) {
                Icon(
                    imageVector = Icons.Default.TaskAlt,
                    contentDescription = "arrow confirm",
                    tint = Color.White
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )

}