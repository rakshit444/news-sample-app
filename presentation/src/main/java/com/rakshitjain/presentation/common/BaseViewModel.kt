package com.rakshitjain.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    private val pendingJobs = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Default + pendingJobs)

    override fun onCleared() {
        coroutineScope.cancel()
    }
}