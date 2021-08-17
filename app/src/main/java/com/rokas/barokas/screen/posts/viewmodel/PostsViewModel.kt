package com.rokas.barokas.screen.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor() : ViewModel() {
    private val mutableScreenStart = MutableLiveData<Int>()

    fun onScreenStart(): Disposable = Single.just(1)
        .subscribe { result ->
            mutableScreenStart.value = result
        }

    fun getMessageResIdLiveData() = mutableScreenStart as LiveData<Int>
}
