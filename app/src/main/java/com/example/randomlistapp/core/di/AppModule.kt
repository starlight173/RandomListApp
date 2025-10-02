package com.example.randomlistapp.core.di

import com.example.randomlistapp.features.items.data.datasources.ItemService
import com.example.randomlistapp.features.items.data.repositories.ItemRepositoryImpl
import com.example.randomlistapp.features.items.domain.repositories.ItemRepository
import com.example.randomlistapp.features.items.services.FakeItemService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindItemService(impl: FakeItemService): ItemService

    @Binds
    @Singleton
    abstract fun bindItemRepository(impl: ItemRepositoryImpl): ItemRepository
}