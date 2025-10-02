package com.example.randomlistapp.features.items.domain.entities

data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)