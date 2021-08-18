package com.rokas.barokas.screen.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rokas.barokas.module.Main
import com.rokas.barokas.screen.details.component.GetPostUseCase
import com.rokas.barokas.screen.posts.model.PostDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    @Main private val mainScheduler: Scheduler
) : ViewModel() {
    private val mutablePost = MutableLiveData<PostDomain>()

    fun getScreenDetails(postId: Int): Disposable = getPostUseCase.getPosts(postId)
        .observeOn(mainScheduler)
        .subscribe(
            { result -> mutablePost.value = result },
            { Timber.e(it) }
        )

    fun getPostLiveData() = mutablePost as LiveData<PostDomain>
}
