package com.rakshitjain.domain.common

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

abstract class BaseJobUseCase<T>(private val context: CoroutineContext) {

    abstract suspend fun createJob(data: Map<String, Any>? = null): ReceiveChannel<T>

    fun job(withData: Map<String, Any>? = null): Job {

        return GlobalScope.launch { withContext(context) { createJob(withData) } }
    }
}