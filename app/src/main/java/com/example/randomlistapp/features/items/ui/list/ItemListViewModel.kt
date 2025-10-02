package com.example.randomlistapp.features.items.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomlistapp.core.ui.theme.ViewState
import com.example.randomlistapp.features.items.domain.entities.Item
import com.example.randomlistapp.features.items.domain.repositories.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val repo: ItemRepository
): ViewModel() {
    private val _state = MutableStateFlow<ViewState<List<Item>>>(ViewState.Idle)
    val state: StateFlow<ViewState<List<Item>>> = _state

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            _state.value = ViewState.Loading
            runCatching {
                repo.getItems()
            }.onSuccess { items ->
                _state.value = ViewState.Success(items)
            }.onFailure { error ->
                _state.value = ViewState.Failure(error)
            }
        }
    }
}