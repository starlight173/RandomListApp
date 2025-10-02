package com.example.randomlistapp.features.items.ui.detail

import androidx.lifecycle.SavedStateHandle
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
class ItemDetailViewModel @Inject constructor(
    private val repo: ItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["itemId"])

    private val _state = MutableStateFlow<ViewState<Item>>(ViewState.Idle)
    val state: StateFlow<ViewState<Item>> = _state

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.value = ViewState.Loading
            runCatching { repo.getItemById(id) }
                .onSuccess { item ->
                    if (item != null) {
                        _state.value = ViewState.Success(item)
                    } else {
                        _state.value =
                            ViewState.Failure(NoSuchElementException("Item #$id not found"))
                    }
                }
                .onFailure { error ->
                    _state.value = ViewState.Failure(error)
                }
        }
    }
}