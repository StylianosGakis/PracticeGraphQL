package xyz.stylianosgakis.practicegraphql.feature.launchdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import xyz.stylianosgakis.apollo.LaunchDetailsQuery
import xyz.stylianosgakis.practicegraphql.util.getStateFlow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    apolloClient: ApolloClient,
) : ViewModel() {
    private val launchIdState: StateFlow<String?> =
        savedStateHandle.getStateFlow<String?>(viewModelScope, "id", null)

    @OptIn(FlowPreview::class)
    val viewState: StateFlow<LaunchDetailViewState> = launchIdState
        .filterNotNull()
        .flatMapConcat { id: String ->
            apolloClient.query(LaunchDetailsQuery(id)).toFlow()
        }
        .map { response: ApolloResponse<LaunchDetailsQuery.Data> ->
            if (response.hasErrors()) {
                return@map LaunchDetailViewState.Error
            }
            val launch = response.data?.launch ?: return@map LaunchDetailViewState.Error
            LaunchDetailViewState.Data(launch)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LaunchDetailViewState.Loading
        )
}
