package com.rokas.barokas.screen.details.component

import com.rokas.barokas.screen.posts.model.PostDomain
import com.rokas.barokas.screen.posts.model.PostsLocalRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postsLocalRepository: PostsLocalRepository
) {
    fun getPost(postId: Int): Single<PostDomain> = postsLocalRepository.getPost(postId)
}