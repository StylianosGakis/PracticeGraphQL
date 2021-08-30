package xyz.stylianosgakis.practicegraphql.util

import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

/**
 * Retrieved from https://gist.github.com/marcellogalhardo/2a1ec56b7d00ba9af1ec9fd3583d53dc
 *
 * Returns a [StateFlow] that accesses data associated with the given key.
 *
 * @param scope The scope used to synchronize the [StateFlow] and [SavedStateHandle]
 * @param key The identifier for the value
 * @param initialValue If no value exists with the given [key], a new one is created
 *  with the given [initialValue].
 *
 * @see SavedStateHandle.getLiveData
 */
fun <T> SavedStateHandle.getStateFlow(
    scope: CoroutineScope,
    key: String,
    initialValue: T
): MutableStateFlow<T> {
    val liveData = getLiveData(key, initialValue)
    val stateFlow = MutableStateFlow(initialValue)

    // Synchronize the LiveData with the StateFlow
    val observer = Observer<T> { value ->
        stateFlow.value = value
    }
    liveData.observeForever(observer)

    stateFlow.onCompletion {
        // Stop observing the LiveData if the StateFlow completes
        withContext(Dispatchers.Main.immediate) {
            liveData.removeObserver(observer)
        }
    }.onEach { value ->
        // Synchronize the StateFlow with the LiveData
        withContext(Dispatchers.Main.immediate) {
            if (liveData.value != value) {
                liveData.value = value
            }
        }
    }.launchIn(scope)

    return stateFlow
}
