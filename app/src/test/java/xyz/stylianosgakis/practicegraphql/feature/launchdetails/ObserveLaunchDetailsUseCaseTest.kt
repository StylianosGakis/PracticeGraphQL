package xyz.stylianosgakis.practicegraphql.feature.launchdetails

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.apollographql.apollo3.testing.MapTestNetworkTransport
import com.apollographql.apollo3.testing.QueueTestNetworkTransport
import com.apollographql.apollo3.testing.enqueueTestResponse
import com.apollographql.apollo3.testing.registerTestResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import xyz.stylianosgakis.apollo.LaunchDetailsQuery
import xyz.stylianosgakis.apollo.test.LaunchDetailsQuery_TestBuilder.Data
import xyz.stylianosgakis.practicegraphql.apollo.OperationMapTestNetworkTransport
import xyz.stylianosgakis.practicegraphql.apollo.registerMapTestNetworkError
import xyz.stylianosgakis.practicegraphql.apollo.registerMapTestResponse

@OptIn(ExperimentalCoroutinesApi::class, ApolloExperimental::class)
class ObserveLaunchDetailsUseCaseTest {

    private val launchId = "109"
    private val expectedData = LaunchDetailsQuery.Data {
        launch = launch {
            id = launchId
            site = "CCAFS SLC 40"
            mission = mission {
                name = "Starlink-15 (v1.0)"
                missionPatch = "https://images2.imgbox.com/d2/3b/bQaWiil0_o.png"
            }
            rocket = rocket {
                id = "falcon9"
                name = "Falcon 9"
                type = "FT"
            }
            isBooked = false
        }
    }

    @Test
    fun `query over the internet`() = runTest {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
            .build()

        val useCase = ObserveLaunchDetailsUseCase(apolloClient)

        val result = useCase.invoke(launchId).first()

        Assert.assertEquals(expectedData, result.data)
    }

    @Test
    fun `query using queue transport`() = runTest {
        val apolloClient = ApolloClient.Builder()
            .networkTransport(QueueTestNetworkTransport())
            .build()
        apolloClient.enqueueTestResponse(LaunchDetailsQuery(launchId), expectedData)
        val useCase = ObserveLaunchDetailsUseCase(apolloClient)

        val result = useCase.invoke(launchId).first()

        Assert.assertEquals(expectedData, result.data)
    }

    @Test
    fun `query using operation instance map transport`() = runTest {
        val apolloClient = ApolloClient.Builder()
            .networkTransport(MapTestNetworkTransport())
            .build()
        apolloClient.registerTestResponse(LaunchDetailsQuery(launchId), expectedData)
        val useCase = ObserveLaunchDetailsUseCase(apolloClient)

        val result = useCase.invoke(launchId).first()

        Assert.assertEquals(expectedData, result.data)
    }

    @Test
    fun `query using operation type map transport`() = runTest {
        val apolloClient = ApolloClient.Builder()
            .networkTransport(OperationMapTestNetworkTransport())
            .build()
        apolloClient.registerMapTestResponse(
            LaunchDetailsQuery::class,
            LaunchDetailsQuery(""), // Can create a "wrong" instance of the operation here to make it compile
            expectedData
        )
        val useCase = ObserveLaunchDetailsUseCase(apolloClient)

        val result = useCase.invoke(launchId).first()

        Assert.assertEquals(expectedData, result.data)
    }

    @Test
    fun `failing query using operation type map transport`() = runTest {
        val apolloClient = ApolloClient.Builder()
            .networkTransport(OperationMapTestNetworkTransport())
            .build()
        apolloClient.registerMapTestNetworkError(LaunchDetailsQuery::class)
        val useCase = ObserveLaunchDetailsUseCase(apolloClient)

        var didThrow = false
        try {
            useCase.invoke(launchId).first()
        } catch (e: ApolloNetworkException) {
            didThrow = true
        }
        Assert.assertTrue(didThrow)
    }
}
