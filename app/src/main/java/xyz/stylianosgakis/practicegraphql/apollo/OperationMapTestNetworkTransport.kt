package xyz.stylianosgakis.practicegraphql.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Error
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.apollographql.apollo3.network.NetworkTransport
import com.benasher44.uuid.uuid4
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.reflect.KClass

private sealed interface TestResponse {
    object NetworkError : TestResponse
    class Response(val response: ApolloResponse<out Operation.Data>) : TestResponse
}

@ApolloExperimental
class OperationMapTestNetworkTransport : NetworkTransport {
    private val lock = reentrantLock()
    private val operationTypesToResponses: MutableMap<KClass<out Operation<out Operation.Data>>, TestResponse> =
        mutableMapOf()

    override fun <D : Operation.Data> execute(request: ApolloRequest<D>): Flow<ApolloResponse<D>> {
        val response = lock.withLock { operationTypesToResponses[request.operation::class] }
            ?: error("No response registered for operation ${request.operation}")
        if (response is TestResponse.NetworkError) throw ApolloNetworkException("Network error mapped in OperationMapTestNetworkTransport")
        @Suppress("UNCHECKED_CAST")
        val apolloResponse = (response as TestResponse.Response).response as ApolloResponse<D>
        return flowOf(apolloResponse.newBuilder().isLast(true).build())
    }

    fun <D : Operation.Data> register(
        operationClass: KClass<out Operation<D>>,
        response: ApolloResponse<D>,
    ) {
        lock.withLock {
            operationTypesToResponses[operationClass] = TestResponse.Response(response)
        }
    }

    fun <D : Operation.Data> registerNetworkError(operationClass: KClass<out Operation<D>>) {
        lock.withLock {
            operationTypesToResponses[operationClass] = TestResponse.NetworkError
        }
    }

    override fun dispose() {}
}

@ApolloExperimental
fun <D : Operation.Data> ApolloClient.registerMapTestResponse(
    operation: KClass<out Operation<D>>,
    response: ApolloResponse<D>,
) = (networkTransport as? OperationMapTestNetworkTransport)?.register(operation, response)
    ?: error("Apollo: ApolloClient.registerMapTestResponse() can be used only with OperationMapTestNetworkTransport")

@ApolloExperimental
fun <D : Operation.Data> ApolloClient.registerMapTestResponse(
    operationClass: KClass<out Operation<D>>,
    data: D? = null,
    errors: List<Error>? = null,
) = registerMapTestResponse(
    operationClass,
    ApolloResponse.Builder(
        operation = dummyOperation(),
        requestUuid = uuid4(),
        data = data
    )
        .errors(errors)
        .build()
)

@ApolloExperimental
fun <D : Operation.Data> ApolloClient.registerMapTestNetworkError(operation: KClass<out Operation<D>>) =
    (networkTransport as? OperationMapTestNetworkTransport)?.registerNetworkError(operation)
        ?: error("Apollo: ApolloClient.registerTestNetworkError() can be used only with OperationMapTestNetworkTransport")

fun <D : Operation.Data> dummyOperation(): Operation<D> {
    return object : Operation<D> {
        override fun adapter(): Adapter<D> {
            TODO("Not yet implemented")
        }

        override fun document(): String {
            TODO("Not yet implemented")
        }

        override fun id(): String {
            TODO("Not yet implemented")
        }

        override fun name(): String {
            TODO("Not yet implemented")
        }

        override fun rootField(): CompiledField {
            TODO("Not yet implemented")
        }

        override fun serializeVariables(
            writer: JsonWriter,
            customScalarAdapters: CustomScalarAdapters,
        ) {
            TODO("Not yet implemented")
        }
    }
}
