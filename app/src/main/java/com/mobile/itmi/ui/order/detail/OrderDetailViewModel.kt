package com.mobile.itmi.ui.order.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.itmi.base.BaseViewModel
import com.mobile.itmi.base.ResponseError
import com.mobile.itmi.data.model.Medication
import com.mobile.itmi.domain.GetMedicationDetailUseCase
import com.mobile.itmi.helper.StateWrapper
import com.mobile.itmi.network.NetworkResponse
import kotlinx.coroutines.launch

class OrderDetailViewModel(
    private val getMedicationDetailUseCase: GetMedicationDetailUseCase
) : BaseViewModel() {
    sealed class Event {
        data class OnCreated(val id: String) : Event()
    }

    sealed class State {
        data class ShowLoading(val isLoading: Boolean) : State()
        data class ShowError(val code: Int, val message: String) : State()
        data class ShowNetworkError(val exception: Exception) : State()
        data class ShowData(val medication: Medication) : State()
    }

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state


    fun onEvent(event: Event) {
        when (event) {
            is Event.OnCreated -> getDetailMedication(event.id)
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }

    private fun getDetailMedication(id: String) = launch {
        setState(State.ShowLoading(true))
        when (val response = getMedicationDetailUseCase.execute(id)) {
            is NetworkResponse.Success -> {
                setState(State.ShowData(response.body))
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