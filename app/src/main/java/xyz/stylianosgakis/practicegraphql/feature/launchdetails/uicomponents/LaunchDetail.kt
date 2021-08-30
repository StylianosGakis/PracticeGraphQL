package xyz.stylianosgakis.practicegraphql.feature.launchdetails.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
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
import xyz.stylianosgakis.apollo.LaunchDetailsQuery
import xyz.stylianosgakis.practicegraphql.theme.AppTheme

@Composable
fun LaunchDetail(
    launch: LaunchDetailsQuery.Launch,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(launch.mission?.missionPatch),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(4f)
                    .aspectRatio(1f, false)
            )
            Spacer(Modifier.width(20.dp))
            Column(
                modifier = Modifier.weight(6f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = launch.site ?: "",
                    style = MaterialTheme.typography.subtitle1
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "🚀 ${launch.rocket?.name ?: "unknown name"}",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = launch.rocket?.type ?: "unknown type",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(fraction = 1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Book now (TODO)")
        }
    }
}

@Preview
@Composable
fun LaunchDetailPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            LaunchDetail(
                getPreviewLaunch()
            )
        }
    }
}
