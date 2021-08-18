package com.rokas.barokas.component.network

import com.rokas.barokas.screen.details.model.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UsersService {
    @GET("users")
    fun getUser(): Single<UserResponse>
}