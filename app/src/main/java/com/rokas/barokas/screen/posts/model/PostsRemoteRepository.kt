package com.rokas.barokas.screen.posts.model

import com.rokas.barokas.component.network.PostsService
import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import javax.inject.Inject

class PostsRemoteRepository @Inject constructor(
    private val postsService: PostsService
) {
    fun getPosts(): Single<List<PostDomain>> = postsService.getPosts()
        .map { items -> items.map { it.toDomain() } }
        .doOnError(Timber::e)
}