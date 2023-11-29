package com.company.app.presentation.screen_second

import androidx.compose.ui.focus.FocusState

sealed class SecondScreenEvent {
    data class EnteredTitle(val value: String) : SecondScreenEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : SecondScreenEvent()
    data object SaveEntity : SecondScreenEvent()
}

