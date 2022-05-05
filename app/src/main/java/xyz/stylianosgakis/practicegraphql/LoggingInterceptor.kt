package xyz.stylianosgakis.practicegraphql

import android.util.Log
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : ApolloInterceptor {
    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain,
    ): Flow<ApolloResponse<D>> {
        return chain.proceed(request).onEach { response ->
            val logMessage = """
                request : ${request.operation}
                response: ${response.data}
            """.trimIndent()
            Log.d("Apollo", logMessage)
        }
    }
}
