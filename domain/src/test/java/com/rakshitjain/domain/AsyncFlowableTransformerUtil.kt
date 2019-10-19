package com.rakshitjain.domain

import com.rakshitjain.domain.common.FlowableRxTransformer
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class AsyncFlowableTransformer<T> : FlowableRxTransformer<T>(){
    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }
}