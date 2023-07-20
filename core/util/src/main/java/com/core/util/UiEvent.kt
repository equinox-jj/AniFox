package com.core.util

sealed interface UiEvent {
    data class OnNavigate(val route: String) : UiEvent
    data class ShowSnackBar(val message: String) : UiEvent
    object DismissSnackBar : UiEvent
    object PopBackStack : UiEvent
}