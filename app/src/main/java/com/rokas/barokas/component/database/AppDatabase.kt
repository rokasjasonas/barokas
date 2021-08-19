package com.rokas.barokas.component.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rokas.barokas.component.database.AppDatabase.Companion.DB_VERSION
import com.rokas.barokas.screen.details.model.UserEntity
import com.rokas.barokas.screen.details.model.UsersDao
import com.rokas.barokas.screen.posts.model.PostEntity
import com.rokas.barokas.screen.posts.model.PostsDao

@Database(
    entities = [
        PostEntity::class,
        UserEntity::class
    ],
    version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract val postsDao: PostsDao

    abstract val usersDao: UsersDao

    companion object {
        const val DB_VERSION = 2
    }
}
