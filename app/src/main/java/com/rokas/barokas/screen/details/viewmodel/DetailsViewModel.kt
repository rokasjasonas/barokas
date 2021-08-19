package com.rokas.barokas.screen.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rokas.barokas.module.Main
import com.rokas.barokas.screen.details.component.GetPostUseCase
import com.rokas.barokas.screen.details.component.GetUserUseCase
import com.rokas.barokas.screen.details.model.UserDomain
import com.rokas.barokas.screen.posts.model.PostDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val getUserUseCase: GetUserUseCase,
    @Main private val mainScheduler: Scheduler
) : ViewModel() {
    private val mutablePost = MutableLiveData<PostDomain>()
    private val mutableUser = MutableLiveData<UserDomain>()
    private val mutableError = MutableLiveData<Throwable>()

    fun getScreenDetails(postId: Int): Disposable = getPostUseCase.getPost(postId)
        .observeOn(mainScheduler)
        .subscribe(
            { result ->
                mutablePost.value = result
                getUser(result.userId)
            },
            {
                Timber.e(it)
                mutableError.value = it
            }
        )

    private fun getUser(userId: Int): Disposable = getUserUseCase.getUser(userId)
        .observeOn(mainScheduler)
        .subscribe(
            { result -> mutableUser.value = result },
            {
                Timber.e(it)
                mutableError.value = it
            }
        )

    fun getPostLiveData() = mutablePost as LiveData<PostDomain>

    fun getUserLiveData() = mutableUser as LiveData<UserDomain>

    fun getErrorLiveData() = mutableError as LiveData<Throwable>
}
