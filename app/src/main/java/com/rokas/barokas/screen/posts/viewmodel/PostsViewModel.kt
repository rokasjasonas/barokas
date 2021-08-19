package com.rokas.barokas.screen.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rokas.barokas.module.Main
import com.rokas.barokas.screen.posts.component.GetPostsUseCase
import com.rokas.barokas.screen.posts.model.PostDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    @Main private val mainScheduler: Scheduler
) : ViewModel() {
    private val mutablePosts = MutableLiveData<List<PostDomain>>()
    private val mutableError = MutableLiveData<Throwable>()
    private val mutableCompleted = MutableLiveData<Int>()

    fun getPosts(): Disposable = getPostsUseCase.getPosts()
        .observeOn(mainScheduler)
        .subscribe(
            { result -> mutablePosts.value = result },
            {
                Timber.e(it)
                mutableError.value = it
            },
            { mutableCompleted.value = 1 }
        )

    fun getPostsLiveData() = mutablePosts as LiveData<List<PostDomain>>

    fun getErrorLiveData() = mutableError as LiveData<Throwable>

    fun getCompletedLiveData() = mutableCompleted as LiveData<Int>
}
