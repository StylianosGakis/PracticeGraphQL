package xyz.stylianosgakis.practicegraphql

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
            .build()
    }
}
