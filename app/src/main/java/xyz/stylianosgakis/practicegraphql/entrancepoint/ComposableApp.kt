package xyz.stylianosgakis.practicegraphql.entrancepoint

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import xyz.stylianosgakis.practicegraphql.details.DetailScreen
import xyz.stylianosgakis.practicegraphql.main.MainScreen
import xyz.stylianosgakis.practicegraphql.navigation.Direction
import xyz.stylianosgakis.practicegraphql.navigation.NavigationActions
import xyz.stylianosgakis.practicegraphql.navigation.slideAndFadeIn
import xyz.stylianosgakis.practicegraphql.navigation.slideAndFadeOut

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ComposableApp() {
    val navController = rememberAnimatedNavController()
    val navActions = remember(navController) { NavigationActions(navController) }

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        enterTransition = { _, _ -> slideAndFadeIn(from = Direction.Right) },
        exitTransition = { _, _ -> slideAndFadeOut(to = Direction.Left) },
        popEnterTransition = { _, _ -> slideAndFadeIn(from = Direction.Left) },
        popExitTransition = { _, _ -> slideAndFadeOut(to = Direction.Right) },
    ) {
        composable(Screen.Main.route) {
            MainScreen(navActions)
        }
        composable(Screen.Detail.route) {
            DetailScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail")
}
