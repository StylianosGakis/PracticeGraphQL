package xyz.stylianosgakis.practicegraphql.feature.launchdetails

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.stylianosgakis.practicegraphql.feature.launchdetails.uicomponents.LaunchDetail
import xyz.stylianosgakis.practicegraphql.feature.launchdetails.uicomponents.getPreviewLaunch
import xyz.stylianosgakis.practicegraphql.theme.AppTheme

@Composable
fun LaunchDetailScreen() {
    val viewModel: LaunchDetailViewModel = hiltViewModel()

    val launchDetailViewState by viewModel.viewState.collectAsState()
    LaunchDetailScreen(launchDetailViewState)
}

@Composable
private fun LaunchDetailScreen(
    viewState: LaunchDetailViewState
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (viewState) {
                is LaunchDetailViewState.Data -> {
                    LaunchDetail(
                        launch = viewState.launch,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                LaunchDetailViewState.Error -> {
                    Text("Error")
                }
                LaunchDetailViewState.Loading -> {
                    CircularProgressIndicator(
                        Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LaunchDetailScreenPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            LaunchDetailScreen(
                viewState = LaunchDetailViewState.Data(
                    launch = getPreviewLaunch()
                )
            )
        }
    }
}
