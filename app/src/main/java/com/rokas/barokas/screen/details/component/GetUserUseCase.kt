package com.rokas.barokas.screen.details.component

import com.rokas.barokas.screen.details.model.UserDomain
import com.rokas.barokas.screen.details.model.UsersRemoteRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val usersRemoteRepository: UsersRemoteRepository
) {
    fun getUser(userId: Int): Single<UserDomain> = usersRemoteRepository.getUser(userId)
}