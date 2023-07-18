package com.schema.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.http.LoggingInterceptor
import org.koin.dsl.module

val apolloModule = module {
    single { apolloClient(get()) }
}

fun apolloClient(context: Context): ApolloClient {
    val cachedFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)
        .chain(SqlNormalizedCacheFactory(context, "apollo.db"))

    return ApolloClient.Builder()
        .serverUrl("https://graphql.anilist.co")
        .addHttpInterceptor(LoggingInterceptor(LoggingInterceptor.Level.BODY))
        .normalizedCache(cachedFactory)
        .build()
}