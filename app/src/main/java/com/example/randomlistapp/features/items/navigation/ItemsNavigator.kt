package com.example.randomlistapp.features.items.navigation

import androidx.navigation.NavHostController

class ItemsNavigator(private val navController: NavHostController) {
    fun up() = navController.popBackStack()
    fun detail(itemId: Int) = navController.navigate(ItemsDestinations.detail(itemId))
}