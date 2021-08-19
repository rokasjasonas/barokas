package com.rokas.barokas.module

import android.content.Context
import androidx.room.Room
import com.rokas.barokas.component.database.AppDatabase
import com.rokas.barokas.screen.details.model.UsersDao
import com.rokas.barokas.screen.posts.model.PostsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUsersDao(appDatabase: AppDatabase): UsersDao = appDatabase.usersDao

    @Provides
    fun providePostsDao(appDatabase: AppDatabase): PostsDao = appDatabase.postsDao

    private const val DB_NAME = "app_database"
}
