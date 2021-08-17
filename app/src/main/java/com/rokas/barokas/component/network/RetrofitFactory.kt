package com.rokas.barokas.component.network

import com.google.gson.Gson
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitFactory @Inject constructor(
    private val endpoint: String,
    private val gson: Gson
) {
    fun create(): Retrofit {
        val rxJava3CallAdapterFactory = RxJava3CallAdapterFactory.createWithScheduler(
            Schedulers.io()
        )
        return Retrofit.Builder()
            .baseUrl(endpoint)
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}