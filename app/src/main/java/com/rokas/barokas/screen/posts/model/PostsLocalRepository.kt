package com.rokas.barokas.screen.posts.model

import com.rokas.barokas.component.database.AppDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostsLocalRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {
    fun insertAll(posts: List<PostDomain>): Completable =
        appDatabase.postsDao.insertAllPosts(posts.map { it.toEntity() })

    fun getPosts(): Observable<List<PostDomain>> = appDatabase.postsDao.getPosts()
        .map { postEntities ->
            postEntities.map { entity ->
                entity.toDomain()
            }
        }
}