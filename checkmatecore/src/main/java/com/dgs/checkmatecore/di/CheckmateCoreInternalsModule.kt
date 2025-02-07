package com.dgs.checkmatecore.di

import checkmate.CheckmateCore
import checkmate.CheckmateCoreBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class CheckmateCoreInternalsModule {
    @Provides
    fun provideCheckmateCore(): CheckmateCore {
        val builder = CheckmateCoreBuilder()
        return builder.build()
    }
}