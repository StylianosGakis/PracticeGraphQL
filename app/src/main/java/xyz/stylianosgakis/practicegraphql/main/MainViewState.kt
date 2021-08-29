package xyz.stylianosgakis.practicegraphql.main

import xyz.stylianosgakis.apollo.LaunchListQuery

sealed class MainViewState {
    object Loading : MainViewState()
    object Error : MainViewState()
    data class Data(
        val data: LaunchListQuery.Launches
    ) : MainViewState()
}
