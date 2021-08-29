package xyz.stylianosgakis.practicegraphql.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import xyz.stylianosgakis.practicegraphql.navigation.NavigationActions

@Composable
fun MainScreen(navActions: NavigationActions) {
    Column {
        Text(text = "Main Screen")
        Button(onClick = { navActions.goToDetailsScreen() }) {
            Text(text = "Go to details screen")
        }
    }
}
