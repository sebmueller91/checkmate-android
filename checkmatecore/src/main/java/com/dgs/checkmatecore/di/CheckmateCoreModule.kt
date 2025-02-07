package com.dgs.checkmatecore.di

import com.dgs.checkmatecore.CheckmateCoreProvider
import com.dgs.checkmatecore.CheckmateCoreProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CheckmateCoreModule {
    @Binds
    @Singleton
    abstract fun bindCheckmateCoreProvider(impl: CheckmateCoreProviderImpl): CheckmateCoreProvider
}