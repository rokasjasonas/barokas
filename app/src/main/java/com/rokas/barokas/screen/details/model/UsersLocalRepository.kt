package com.rokas.barokas.screen.details.model

import com.rokas.barokas.module.Io
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class UsersLocalRepository @Inject constructor(
    private val usersDao: UsersDao,
    @Io private val ioScheduler: Scheduler
) {
    fun insert(user: UserDomain): Completable =
        usersDao.insertUser(user.toEntity())
            .subscribeOn(ioScheduler)

    fun getUser(userId: Int): Observable<UserDomain> = usersDao.getUser(userId)
        .map { it.toDomain() }
        .subscribeOn(ioScheduler)
        .toObservable()
        .take(1)
}