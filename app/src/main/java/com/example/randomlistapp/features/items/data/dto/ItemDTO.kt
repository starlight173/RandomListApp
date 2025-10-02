package com.example.randomlistapp.features.items.data.dto

import com.example.randomlistapp.features.items.domain.entities.Item

data class ItemDTO(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)

fun ItemDTO.toDomain() = Item(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl
)