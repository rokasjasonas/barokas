package com.rokas.barokas.screen.details.model

import com.rokas.barokas.component.network.UsersService
import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import javax.inject.Inject

class UsersRemoteRepository @Inject constructor(
    private val usersService: UsersService
) {
    fun getUser(userId: Int): Single<UserDomain> = usersService.getUser(userId)
        .map { it.toDomain() }
        .doOnError(Timber::e)
}