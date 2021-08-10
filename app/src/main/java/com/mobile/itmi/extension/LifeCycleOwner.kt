package com.mobile.itmi.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.mobile.itmi.helper.StateWrapper

inline fun <T> LifecycleOwner.subscribeSingleLiveEvent(
    liveData: LiveData<StateWrapper<T>>, crossinline onEventUnhandled: (T) -> Unit
) {
    liveData.observe(this, { it?.getEventIfNotHandled()?.let(onEventUnhandled) })
}
