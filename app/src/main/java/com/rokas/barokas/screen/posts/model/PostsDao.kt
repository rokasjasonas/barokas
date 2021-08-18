package com.rokas.barokas.screen.posts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(posts: List<PostEntity>): Completable

    @Query("DELETE FROM ${PostEntity.TABLE_NAME}")
    fun deleteAllPosts(): Completable

    @Query("SELECT * FROM ${PostEntity.TABLE_NAME}")
    fun getPosts(): Observable<List<PostEntity>>

    @Query("SELECT * FROM ${PostEntity.TABLE_NAME} WHERE id = :postId")
    fun getPost(postId: Int): Single<PostEntity>
}
