package com.example.randomlistapp.features.items.data.datasources

import com.example.randomlistapp.features.items.data.dto.ItemDTO

interface  ItemService {
    suspend fun fetchItems(count: Int = 50): List<ItemDTO>
    suspend fun fetchItemById(id: Int): ItemDTO?
}