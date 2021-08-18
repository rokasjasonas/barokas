package com.rokas.barokas.component.network

import com.rokas.barokas.screen.details.model.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersService {
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Single<UserResponse>
}