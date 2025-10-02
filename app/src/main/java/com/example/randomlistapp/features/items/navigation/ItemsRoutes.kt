package com.example.randomlistapp.features.items.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object ItemsDestinations {
    const val Graph = "items_graph"
    const val List = "items_list"
    const val Detail = "items_detail/{itemId}"

    fun detail(itemId: Int) = "items_detail/$itemId"

    val detailArgs = listOf(
        navArgument("itemId") { type = NavType.IntType }
    )
}