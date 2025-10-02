package com.example.randomlistapp.features.items.data.repositories

import com.example.randomlistapp.features.items.data.datasources.ItemService
import com.example.randomlistapp.features.items.data.dto.toDomain
import com.example.randomlistapp.features.items.domain.entities.Item
import com.example.randomlistapp.features.items.domain.repositories.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val service: ItemService
) : ItemRepository {
    override suspend fun getItems(count: Int): List<Item> = service.fetchItems(count).map {
        it.toDomain()
    }

    override suspend fun getItemById(id: Int): Item? = service.fetchItemById(id)?.toDomain()
}