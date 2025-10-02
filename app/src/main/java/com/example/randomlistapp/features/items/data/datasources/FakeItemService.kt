package com.example.randomlistapp.features.items.services

import com.example.randomlistapp.features.items.data.datasources.ItemService
import com.example.randomlistapp.features.items.data.dto.ItemDTO
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeItemService @Inject constructor() : ItemService {
    private fun imageUrl(seed: Int) = "https://picsum.photos/seed/$seed/600/400"

    override suspend fun fetchItems(count: Int): List<ItemDTO>{
        delay(150)
        return (1..50).map {
            ItemDTO(
                id = it,
                title = "Item $it",
                description = "This is the description for item $it. It is a great item that you will love!",
                imageUrl = imageUrl(it)
            )
        }
    }

    override suspend fun fetchItemById(id: Int): ItemDTO? {
        delay(120)
        return ItemDTO(
            id = id,
            title = "Item $id",
            description = "This is the description for item $id. It is a great item that you will love!",
            imageUrl = imageUrl(id)
        )
    }
}