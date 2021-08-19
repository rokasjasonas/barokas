package com.rokas.barokas.screen.details.component

import com.rokas.barokas.screen.details.model.UserDomain
import com.rokas.barokas.screen.details.model.UsersLocalRepository
import com.rokas.barokas.screen.details.model.UsersRemoteRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val usersRemoteRepository: UsersRemoteRepository,
    private val usersLocalRepository: UsersLocalRepository
) {
    fun getUser(userId: Int): Observable<UserDomain> = Observable.mergeDelayError(
        usersLocalRepository.getUser(userId).onErrorComplete(),
        usersRemoteRepository.getUser(userId)
            .flatMapCompletable { usersLocalRepository.insert(it) }
            .toObservable<UserDomain>()
            .take(1)
    )
}