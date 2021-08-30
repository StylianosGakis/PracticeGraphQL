package xyz.stylianosgakis.practicegraphql.feature.launches.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.stylianosgakis.apollo.LaunchListQuery
import xyz.stylianosgakis.practicegraphql.theme.AppTheme

@Composable
fun LaunchList(
    launchList: List<LaunchListQuery.Launch>,
    goToDetailScreen: (id: String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = launchList,
            key = { launch: LaunchListQuery.Launch ->
                launch.id
            },
        ) { launch: LaunchListQuery.Launch ->
            LaunchCard(
                launch,
                goToDetailScreen
            )
        }
    }
}

@Preview
@Composable
fun LaunchListPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            LaunchList(
                launchList = List(10, ::getPreviewLaunch)
            ) {}
        }
    }
}
