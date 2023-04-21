package com.example.mynotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.ui.screens.Screen
import com.example.mynotes.ui.screens.home.Home
import com.example.mynotes.ui.screens.home.NotesScreen
import com.example.mynotes.ui.screens.note.NewNoteScreen

/** @Composable
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


private fun NavGraphBuilder.notesNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.NOTES).route,
        route = Feature.NOTES.route
    ) {

        composable(NavCommand.ContentType(Feature.NOTES)) {

            NotesScreen()

        }
    }
}


private fun NavGraphBuilder.newNoteNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.NEWNOTE).route,
        route = Feature.NEWNOTE.route
    ) {

        composable(NavCommand.ContentType(Feature.NEWNOTE)) {

            NewNoteScreen(onClickBackStack = { navController.popBackStack() } )

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
}  **/

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object NewNote : Screen("newnote")

    /** object Detail : Screen("detail/{${NavArgs.ItemId.key}}") {
        fun createRoute(id: Int) = "detail/$id"
    } **/
}

/** enum class NavArgs(val key: String) {
    ItemId("itemId")
} **/


@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    Screen {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) { backStackEntry ->
                Home(onNewNoteClick = {
                    navController.navigate(Screen.NewNote.route) {
                        popUpTo(backStackEntry.destination.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }

            composable(Screen.NewNote.route) {
                NewNoteScreen(onClickBackStack = { navController.popBackStack() } )
            }
        }
    }
}

