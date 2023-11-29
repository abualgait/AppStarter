package com.company.app.presentation.screen_first

import com.company.app.domain.model.AppEntity

sealed class FirstScreenEvent {
    data object GetData : FirstScreenEvent()
    data class DeleteData(val appEntity: AppEntity) : FirstScreenEvent()
}
