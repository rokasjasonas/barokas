package com.rokas.barokas.component.network

import com.rokas.barokas.screen.posts.model.PostResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PostsService {
    @GET("posts")
    fun getPosts(): Single<List<PostResponse>>
}