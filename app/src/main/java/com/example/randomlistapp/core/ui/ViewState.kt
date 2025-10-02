package com.example.randomlistapp.core.ui.theme

sealed interface ViewState<out T> {
    data object Idle : ViewState<Nothing>
    data object Loading : ViewState<Nothing>
    data class Success<T>(val data: T) : ViewState<T>
    data class Failure(val throwable: Throwable) : ViewState<Nothing>
}