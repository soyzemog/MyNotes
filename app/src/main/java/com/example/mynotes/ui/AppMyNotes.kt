package com.example.mynotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mynotes.ui.component.BottomBar
import com.example.mynotes.ui.component.FloatingButton
import com.example.mynotes.ui.component.TopBar
import com.example.mynotes.ui.navigation.Navigation
import com.example.mynotes.ui.theme.MyNotesTheme


@Composable
fun AppMyNotes() {

    val appState = rememberAppMyNotesState()

    ScreenMyNotes {
        Scaffold(
            topBar = {
                TopBar(appState)
            },
            floatingActionButton = {
                if (appState.showFloatingButton) {
                    FloatingButton(appState)
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            bottomBar = {
                if (appState.showBottomBar) {
                    BottomBar()
                }
            },
            scaffoldState = rememberScaffoldState()
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = appState.navController)
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