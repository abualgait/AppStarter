package com.company.app.presentation.screen_first

import com.company.app.domain.model.AppDomainModel

data class FirstScreenState(
    val data: List<AppDomainModel> = emptyList(),
)