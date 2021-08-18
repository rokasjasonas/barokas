package com.rokas.barokas.module

import com.google.gson.Gson
import com.rokas.barokas.BuildConfig
import com.rokas.barokas.component.network.PostsService
import com.rokas.barokas.component.network.RetrofitFactory
import com.rokas.barokas.component.network.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit =
        RetrofitFactory(BuildConfig.BACKEND_URL, gson).create()

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit): PostsService =
        retrofit.create(PostsService::class.java)

    @Singleton
    @Provides
    fun provideUsersService(retrofit: Retrofit): UsersService =
        retrofit.create(UsersService::class.java)

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()
}