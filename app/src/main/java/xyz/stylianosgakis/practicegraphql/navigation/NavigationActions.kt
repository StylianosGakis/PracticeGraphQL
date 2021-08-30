package xyz.stylianosgakis.practicegraphql.navigation

import androidx.navigation.NavHostController
import xyz.stylianosgakis.practicegraphql.entrancepoint.Screen

class NavigationActions(
    private val navController: NavHostController
) {
    fun goToDetailsScreen(id: String) {
        navController.navigate(Screen.Detail.createRoute(id))
    }
}
