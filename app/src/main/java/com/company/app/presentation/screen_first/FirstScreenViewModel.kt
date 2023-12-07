package com.company.app.presentation.screen_first

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.app.domain.model.AppDomainModel
import com.company.app.domain.use_case.AppUseCases
import com.company.app.util.ConnectivityManager
import com.company.app.util.DialogQueue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    private val connectivityManager: ConnectivityManager,
) : ViewModel() {

    val dialogQueue = DialogQueue()

    var loading = mutableStateOf(false)
        private set

    var isConnected = mutableStateOf(false)
        private set

    private val _state = mutableStateOf(FirstScreenState())
    val state: State<FirstScreenState> = _state


    private var getDataJob: Job? = null

    init {
        getData()
    }

    fun onEvent(event: FirstScreenEvent) {
        when (event) {
            is FirstScreenEvent.GetData -> {
                getData()
            }

            is FirstScreenEvent.DeleteData -> {
                deleteData(event.appEntity)
            }
        }
    }

    private fun getData() {
        loading.value = true
        getDataJob?.cancel()
        getDataJob = appUseCases.getListOfData()
            .onEach { data ->
                _state.value = state.value.copy(
                    data = data
                )
            }
            .launchIn(viewModelScope)
    }

    private fun deleteData(appDomainModel: AppDomainModel) {
        loading.value = true
        viewModelScope.launch { appUseCases.deleteData(appDomainModel) }
    }
}