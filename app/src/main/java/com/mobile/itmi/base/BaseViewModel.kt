package com.mobile.itmi.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.mobile.itmi.network.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    internal fun NetworkResponse<Any, ResponseError>.setErrorResponse(
        onServerError: (code: Int, message: String) -> Unit,
        onNetworkError: (Exception) -> Unit,
        onUnKnowError: ((Throwable) -> Unit)? = null
    ) {
        if (this is NetworkResponse.Success) return
        when (this) {
            is NetworkResponse.ServerError -> {
                onServerError(this.code, this.body?.message ?: "")
            }
            is NetworkResponse.NetworkError -> onNetworkError(this.error)
            is NetworkResponse.UnknownError -> {
                Log.e(this.javaClass.simpleName, this.error.message ?: "")
                onUnKnowError?.invoke(this.error)
            }
            else -> {
                // no - op
            }
        }
    }
}