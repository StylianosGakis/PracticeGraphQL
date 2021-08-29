package xyz.stylianosgakis.practicegraphql.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toFlow
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
class MainViewModel @Inject constructor(
    apolloClient: ApolloClient,
) : ViewModel() {
    val viewState: StateFlow<MainViewState> = apolloClient
        .query(LaunchListQuery())
        .watcher()
        .toFlow()
        .map { response: Response<LaunchListQuery.Data> ->
            if (response.hasErrors()) {
                return@map MainViewState.Error
            }
            val responseData = response.data ?: return@map MainViewState.Error
            MainViewState.Data(responseData.launches)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainViewState.Loading
        )
}
