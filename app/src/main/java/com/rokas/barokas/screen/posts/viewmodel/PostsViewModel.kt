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

    fun getPosts(): Disposable {
        return getPostsUseCase.getPosts()
            .observeOn(mainScheduler)
            .subscribe(
                { result -> mutablePosts.value = result },
                {
                    Timber.e(it)
                    mutableError.value = it
                }
            )
    }

    fun getPostsLiveData() = mutablePosts as LiveData<List<PostDomain>>

    fun getErrorLiveData() = mutableError as LiveData<Throwable>
}
