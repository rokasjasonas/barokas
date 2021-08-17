package com.rokas.barokas.screen.posts.model

data class PostDomain(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)