package com.company.app.presentation.screen_first

import com.company.app.domain.model.AppEntity

data class FirstScreenState(
    val data: List<AppEntity> = emptyList(),
)