package com.rokas.barokas.screen.details.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity): Completable

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE id = :userId")
    fun getUser(userId: Int): Single<UserEntity>
}
