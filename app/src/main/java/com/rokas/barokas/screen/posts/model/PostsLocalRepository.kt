package com.rokas.barokas.screen.posts.model

import com.rokas.barokas.component.database.AppDatabase
import com.rokas.barokas.module.Io
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostsLocalRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    @Io private val ioScheduler: Scheduler
) {
    fun insertAll(posts: List<PostDomain>): Completable =
        appDatabase.postsDao.insertAllPosts(posts.map { it.toEntity() })
            .subscribeOn(ioScheduler)

    fun getPosts(): Observable<List<PostDomain>> = appDatabase.postsDao.getPosts()
        .map { postEntities ->
            postEntities.map { entity ->
                entity.toDomain()
            }
        }
        .subscribeOn(ioScheduler)

    fun getPost(postId: Int): Single<PostDomain> = appDatabase.postsDao.getPost(postId)
        .map { it.toDomain() }
        .subscribeOn(ioScheduler)
}