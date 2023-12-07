package com.company.app.presentation.screen_first

import com.company.app.domain.model.AppDomainModel

sealed class FirstScreenEvent {
    data object GetData : FirstScreenEvent()
    data class DeleteData(val appEntity: AppDomainModel) : FirstScreenEvent()
}
