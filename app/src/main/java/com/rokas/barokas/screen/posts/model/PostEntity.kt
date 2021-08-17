package com.rokas.barokas.screen.posts.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rokas.barokas.screen.posts.model.PostEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PostEntity(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
) {
    fun toDomain() = PostDomain(
        userId,
        id,
        title,
        body
    )

    companion object {
        const val TABLE_NAME = "posts"
    }
}

