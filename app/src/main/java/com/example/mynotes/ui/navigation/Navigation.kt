package com.example.mynotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mynotes.ui.screens.home.NotesScreen
import com.example.mynotes.ui.screens.note.NewNoteScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.NOTES.route
    ) {
        // grafo de navegacion
        notesNav(navController)
        newNoteNav(navController)
    }
}


private fun NavGraphBuilder.notesNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.NOTES).route,
        route = Feature.NOTES.route
    ) {

        composable(NavCommand.ContentType(Feature.NOTES)) {

            NotesScreen()

        }
    }
}


private fun NavGraphBuilder.newNoteNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.NEWNOTE).route,
        route = Feature.NEWNOTE.route
    ) {

        composable(NavCommand.ContentType(Feature.NEWNOTE)) {

            NewNoteScreen()

        }

    }
}


private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

