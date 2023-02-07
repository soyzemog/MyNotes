package com.example.mynotes.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mynotes.ui.common.BottomNav
import com.example.mynotes.ui.home.ScreenHome
import com.example.mynotes.ui.theme.MyNotesTheme


@Composable
fun AppMyNotes() {

    ScreenMyNotes {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(
                        text = "My Notes",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )},
                    backgroundColor = MaterialTheme.colors.background
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.Black
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            bottomBar = {
                BottomAppBar(
                    backgroundColor = MaterialTheme.colors.background,
                    cutoutShape = CircleShape
                ) {
                    BottomNav()
                }
            },
            scaffoldState = rememberScaffoldState()
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ScreenHome()
            }
        }

    }

}


@Composable
fun ScreenMyNotes(content: @Composable () -> Unit) {
    MyNotesTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}