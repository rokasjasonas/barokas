package com.rokas.barokas.screen.posts.component

import com.rokas.barokas.screen.posts.model.PostDomain
import com.rokas.barokas.screen.posts.model.PostsLocalRepository
import com.rokas.barokas.screen.posts.model.PostsRemoteRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRemoteRepository: PostsRemoteRepository,
    private val postsLocalRepository: PostsLocalRepository
) {
    fun getPosts(): Observable<List<PostDomain>> = Observable.concatDelayError(
        listOf(
            postsLocalRepository.getPosts(),
            postsRemoteRepository.getPosts()
                .flatMapObservable {
                    postsLocalRepository.insertAll(it).andThen(Observable.just(it))
                }
                .take(1)
        )
    )
}