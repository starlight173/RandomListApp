package com.example.randomlistapp.features.items.domain.repositories

import com.example.randomlistapp.features.items.domain.entities.Item

interface ItemRepository {
    suspend fun getItems(count: Int = 50): List<Item>
    suspend fun getItemById(id: Int): Item?
}