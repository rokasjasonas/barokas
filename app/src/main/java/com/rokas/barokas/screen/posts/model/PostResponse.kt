package com.rokas.barokas.screen.posts.model

data class PostResponse(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
) {
    fun toDomain(): PostDomain = PostDomain(userId, id, title, body)
}