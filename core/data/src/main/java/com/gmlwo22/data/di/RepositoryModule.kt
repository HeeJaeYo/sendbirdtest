package com.gmlwo22.data.di

import com.gmlwo22.data.repository.ITBookStoreRepositoryImpl
import com.gmlwo22.domain.repository.ITBookStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindITBookStoreRepository(itBookStoreRepositoryImpl: ITBookStoreRepositoryImpl): ITBookStoreRepository
}