package com.company.app.presentation.screen_second

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.app.domain.model.AppEntity
import com.company.app.domain.model.InvalidDataException
import com.company.app.domain.use_case.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _entityTitle = mutableStateOf(EntityTextFieldState(hint = "Enter title..."))
    val entityTitle: State<EntityTextFieldState> = _entityTitle

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currententityId: Int? = null

    init {
        savedStateHandle.get<Int>("entityId")?.let { entityId ->
            if (entityId != -1) {
                viewModelScope.launch {
                    appUseCases.getData(entityId)?.also { entity ->
                        currententityId = entity.id
                        _entityTitle.value = entityTitle.value.copy(
                            text = entity.title,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: SecondScreenEvent) {
        when (event) {
            is SecondScreenEvent.EnteredTitle -> {
                _entityTitle.value = entityTitle.value.copy(
                    text = event.value
                )
            }

            is SecondScreenEvent.ChangeTitleFocus -> {
                _entityTitle.value = entityTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            entityTitle.value.text.isBlank()
                )
            }

            is SecondScreenEvent.SaveEntity -> {
                viewModelScope.launch {
                    try {
                        appUseCases.addData(
                            AppEntity(
                                title = entityTitle.value.text,
                                id = currententityId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveEntity)
                    } catch (e: InvalidDataException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save entity"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveEntity : UiEvent()
    }
}