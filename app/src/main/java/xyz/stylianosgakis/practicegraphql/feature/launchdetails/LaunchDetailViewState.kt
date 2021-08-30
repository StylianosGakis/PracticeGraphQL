package xyz.stylianosgakis.practicegraphql.feature.launchdetails

import xyz.stylianosgakis.apollo.LaunchDetailsQuery

sealed class LaunchDetailViewState {
    object Loading : LaunchDetailViewState()
    object Error : LaunchDetailViewState()
    data class Data(
        val launch: LaunchDetailsQuery.Launch
    ) : LaunchDetailViewState()
}
