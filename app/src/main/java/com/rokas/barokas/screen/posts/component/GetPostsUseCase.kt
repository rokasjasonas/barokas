package com.rokas.barokas.screen.posts.component

import com.rokas.barokas.screen.posts.model.PostsRemoteRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRemoteRepository: PostsRemoteRepository
) {
    fun getPosts() = postsRemoteRepository.getPosts()
}