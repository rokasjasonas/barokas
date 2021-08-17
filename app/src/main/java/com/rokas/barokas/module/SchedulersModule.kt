package com.rokas.barokas.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
class SchedulersModule {
    @Main
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Io
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @SingleThread
    @Provides
    fun provideSingleScheduler(): Scheduler = Schedulers.single()
}

@Qualifier
annotation class Io

@Qualifier
annotation class Main

@Qualifier
annotation class SingleThread