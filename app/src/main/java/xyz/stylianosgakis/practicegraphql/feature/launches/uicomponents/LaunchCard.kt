package xyz.stylianosgakis.practicegraphql.feature.launches.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import xyz.stylianosgakis.apollo.LaunchListQuery
import xyz.stylianosgakis.practicegraphql.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchCard(
    launch: LaunchListQuery.Launch,
    goToDetailScreen: (id: String) -> Unit
) {
    Card(
        onClick = { goToDetailScreen(launch.id) },
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {
            Image(
                painter = rememberImagePainter(launch.mission?.missionPatch),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f, true)
            )
            Spacer(Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = launch.mission?.name ?: "",
                    style = MaterialTheme.typography.subtitle1,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = launch.site ?: "",
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LaunchCardPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            LaunchCard(
                launch = getPreviewLaunch()
            ) {}
        }
    }
}
