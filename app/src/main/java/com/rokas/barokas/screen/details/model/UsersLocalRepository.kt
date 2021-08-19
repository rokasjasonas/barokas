package com.rokas.barokas.screen.details.model

import com.rokas.barokas.component.database.AppDatabase
import com.rokas.barokas.module.Io
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersLocalRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    @Io private val ioScheduler: Scheduler
) {
    fun insert(user: UserDomain): Completable =
        appDatabase.usersDao.insertUser(user.toEntity())
            .subscribeOn(ioScheduler)

    fun getUser(userId: Int): Single<UserDomain> = appDatabase.usersDao.getUser(userId)
        .map { it.toDomain() }
        .subscribeOn(ioScheduler)
}