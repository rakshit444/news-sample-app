package com.rakshitjain.presentation.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import rakshitjain.news_sample_app.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String) =
        retrofitClient(baseUrl, httpClient())

class BasicAuthInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
                .header("Authorization", "HiF41qQG5Ysve1TULfqy4T7jFOIo7pW1Z52buyme-B8BTUlkuSglHKi4ITFVCrL2okM8e0uIB29xQwy250mhqA").build()
        return chain.proceed(authenticatedRequest)
    }

}

private fun httpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
   // clientBuilder.addInterceptor(BasicAuthInterceptor())
    clientBuilder.readTimeout(120, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
