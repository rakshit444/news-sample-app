package com.rakshitjain.domain.common

import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext


abstract class BaseJobUseCase<T>(private val coroutineContext: CoroutineContext){

    //Provide you the data channel from the data layer
    abstract suspend fun getDataChannel(data: Map<String, Any>? = null): ReceiveChannel<DataEntity<T>>

    /**
     * @param data from the Data layer
     *
     * Channel is created between domain layer and the presentation layer and any manipulation on
     * data before sending to the presentation layer can be done.
     */
    abstract suspend fun sendToPresentation(data: DataEntity<T>): DataEntity<T>

    fun produce(withData: Map<String, Any>? = null): ReceiveChannel<DataEntity<T>> {

        return GlobalScope.produce(context = coroutineContext) {
            val dataChannel = getDataChannel(withData)
            dataChannel.consumeEach {
                send(sendToPresentation(it))
            }
        }
    }
}