package com.example.mynotes.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.ui.navigation.NavItem

@Composable
fun rememberAppMyNotesState(
    navController: NavHostController = rememberNavController()
): AppMyNotesState = remember(navController) {
    AppMyNotesState(navController)
}


class AppMyNotesState(val navController: NavHostController) {

    // Constantes
    companion object {
    }


    // Properties

    /** BackStackEntry
     * es el q permite acceder a la ruta actual
     * Y necesita de una funcion composable para poder usarse (@Composable get()) **/

    /** si viene alguna ruta vacia, devolvemos cadena vacia **/
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""


    /**
     * muestra boton arrowback
     */
    /** val showUpNavigation: Boolean
        @Composable get() = (
                currentRoute == NavItem.NEWPLAYER.navCommand.route ||
                        currentRoute == NavItem.EDIT.navCommand.route
                ) **/

    val showFloatingButton: Boolean
        @Composable get() = currentRoute == NavItem.NOTES.navCommand.route


    // Logica de UI
    fun onUpClick() {
        navController.popBackStack()
    }


    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

}



fun NavHostController.navigatePoppingUpToStartDestination(route: String) {

    navigate(route) {
        /** navigate genera un dsl el cual permite opciones de navegacion
        quita toda la navegacion anterior, hasta el punto q se defina en el argumento **/
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // evita q se re lanze la navegacion sobre la pantalla q estamos posicionamos
        launchSingleTop = true
        // restaura el estado almacenado en el popUpTo()
        restoreState = true
    }
}