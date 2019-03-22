package com.rakshitjain.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(),CoroutineScope {

    private val pendingJobs = Job()

    override val coroutineContext: CoroutineContext
        get() = (Dispatchers.Default + pendingJobs)


    override fun onCleared() {
        pendingJobs.cancel()
    }
}