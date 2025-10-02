package com.example.randomlistapp.features.items.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.randomlistapp.features.items.ui.detail.DetailScreen
import com.example.randomlistapp.features.items.ui.list.ItemListScreen

fun NavGraphBuilder.itemsNavGraph(
    navControllerProvider: () -> androidx.navigation.NavHostController,
    startDestination: String = ItemsDestinations.List
) {
    navigation(
        startDestination = startDestination,
        route = ItemsDestinations.Graph
    ) {
        composable(ItemsDestinations.List) {
            val actions = remember(navControllerProvider()) { ItemsNavigator(navControllerProvider()) }
            ItemListScreen(
                modifier = Modifier,
                onItemClick = { id -> actions.detail(id) }
            )
        }
        composable(
            route = ItemsDestinations.Detail,
            arguments = ItemsDestinations.detailArgs
        ) {
            val actions = remember(navControllerProvider()) { ItemsNavigator(navControllerProvider()) }
            DetailScreen(
                modifier = Modifier,
                vm = hiltViewModel()
            )
        }
    }
}