package xyz.stylianosgakis.practicegraphql.entrancepoint

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import xyz.stylianosgakis.practicegraphql.feature.launchdetails.LaunchDetailScreen
import xyz.stylianosgakis.practicegraphql.feature.launches.MainScreen
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
        enterTransition = { slideAndFadeIn(from = Direction.Right) },
        exitTransition = { slideAndFadeOut(to = Direction.Left) },
        popEnterTransition = { slideAndFadeIn(from = Direction.Left) },
        popExitTransition = { slideAndFadeOut(to = Direction.Right) },
    ) {
        composable(Screen.Main.route) {
            MainScreen(navActions)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            LaunchDetailScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: String): String = "detail/$id"
    }
}
