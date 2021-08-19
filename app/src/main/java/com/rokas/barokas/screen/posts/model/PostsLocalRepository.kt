package com.rokas.barokas.screen.posts.model

import com.rokas.barokas.module.Io
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostsLocalRepository @Inject constructor(
    private val postsDao: PostsDao,
    @Io private val ioScheduler: Scheduler
) {
    fun insertAll(posts: List<PostDomain>): Completable =
        postsDao.insertAllPosts(posts.map { it.toEntity() })
            .subscribeOn(ioScheduler)

    fun getPosts(): Observable<List<PostDomain>> = postsDao.getPosts()
        .map { postEntities ->
            postEntities.map { entity ->
                entity.toDomain()
            }
        }
        .subscribeOn(ioScheduler)
        .take(1)

    fun getPost(postId: Int): Single<PostDomain> = postsDao.getPost(postId)
        .map { it.toDomain() }
        .subscribeOn(ioScheduler)
}