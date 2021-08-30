package xyz.stylianosgakis.practicegraphql.feature.launches

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.stylianosgakis.practicegraphql.feature.launches.uicomponents.LaunchList
import xyz.stylianosgakis.practicegraphql.feature.launches.uicomponents.getPreviewLaunch
import xyz.stylianosgakis.practicegraphql.navigation.NavigationActions
import xyz.stylianosgakis.practicegraphql.theme.AppTheme

@Composable
fun MainScreen(navActions: NavigationActions) {
    val viewModel: LaunchesViewModel = hiltViewModel()

    val launchesViewState: LaunchesViewState by viewModel.viewState.collectAsState()

    MainScreen(
        viewState = launchesViewState,
        goToDetailScreen = navActions::goToDetailsScreen
    )
}

@Composable
private fun MainScreen(
    viewState: LaunchesViewState,
    goToDetailScreen: (id: String) -> Unit,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (viewState) {
                is LaunchesViewState.Data -> {
                    LaunchList(
                        launchList = viewState.launchList,
                        goToDetailScreen = goToDetailScreen
                    )
                }
                LaunchesViewState.Error -> {
                    Text("Error")
                }
                LaunchesViewState.Loading -> {
                    CircularProgressIndicator(
                        Modifier.align(
                            BiasAlignment(verticalBias = -0.4f, horizontalBias = 0f)
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            MainScreen(
                viewState = LaunchesViewState.Data(
                    List(10, ::getPreviewLaunch)
                ),
                goToDetailScreen = {}
            )
        }
    }
}
