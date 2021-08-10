package com.mobile.itmi.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.itmi.base.BaseViewModel
import com.mobile.itmi.base.ResponseError
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.domain.GetListMedicationUseCase
import com.mobile.itmi.helper.StateWrapper
import com.mobile.itmi.network.NetworkResponse
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val getListMedicationUseCase: GetListMedicationUseCase
) : BaseViewModel() {
    sealed class Event {
        object OnCreated : Event()
    }

    sealed class State {
        data class ShowLoading(val isLoading: Boolean) : State()
        data class ShowError(val code: Int, val message: String) : State()
        data class ShowNetworkError(val exception: Exception) : State()
        data class ShowList(val list: List<Medication>) : State()
    }

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state


    fun onEvent(event: Event) {
        when (event) {
            is Event.OnCreated -> getListMedication()
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }

    private fun getListMedication() = launch {
        setState(State.ShowLoading(true))
        when (val response = getListMedicationUseCase.execute()) {
            is NetworkResponse.Success -> {
                setState(State.ShowList(response.body))
            }
            else -> setError(response)
        }
        setState(State.ShowLoading(false))
    }

    private fun setError(networkResponse: NetworkResponse<Any, ResponseError>) {
        networkResponse.setErrorResponse(
            onServerError = { code, message -> setState(State.ShowError(code, message)) },
            onNetworkError = { exception -> setState(State.ShowNetworkError(exception)) }
        )
    }

}