package xyz.stylianosgakis.practicegraphql.feature.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import xyz.stylianosgakis.apollo.LaunchListQuery
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LaunchesViewModel @Inject constructor(
    apolloClient: ApolloClient,
) : ViewModel() {
    val viewState: StateFlow<LaunchesViewState> = apolloClient
        .query(LaunchListQuery())
        .toFlow()
        .map { response: ApolloResponse<LaunchListQuery.Data> ->
            if (response.hasErrors()) {
                return@map LaunchesViewState.Error
            }
            val responseData: LaunchListQuery.Data =
                response.data ?: return@map LaunchesViewState.Error
            val launchList = responseData
                .launches
                .launches
                .filterNotNull()
            LaunchesViewState.Data(launchList)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LaunchesViewState.Loading
        )
}
