package xyz.stylianosgakis.practicegraphql.feature.launches

import xyz.stylianosgakis.apollo.LaunchListQuery

sealed class LaunchesViewState {
    object Loading : LaunchesViewState()
    object Error : LaunchesViewState()
    data class Data(
        val launchList: List<LaunchListQuery.Launch>
    ) : LaunchesViewState()
}
