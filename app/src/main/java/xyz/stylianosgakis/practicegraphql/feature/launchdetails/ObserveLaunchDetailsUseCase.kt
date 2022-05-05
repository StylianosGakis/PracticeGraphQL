package xyz.stylianosgakis.practicegraphql.feature.launchdetails

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import xyz.stylianosgakis.apollo.LaunchDetailsQuery
import javax.inject.Inject

class ObserveLaunchDetailsUseCase @Inject constructor(
    private val apolloClient: ApolloClient,
) {
    fun invoke(launchId: String): Flow<ApolloResponse<LaunchDetailsQuery.Data>> {
        return apolloClient.query(LaunchDetailsQuery(launchId)).toFlow()
    }
}
