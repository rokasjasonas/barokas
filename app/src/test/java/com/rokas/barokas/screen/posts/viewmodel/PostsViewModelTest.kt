package com.rokas.barokas.screen.posts.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rokas.barokas.screen.posts.component.GetPostsUseCase
import com.rokas.barokas.screen.posts.model.PostDomain
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class PostsViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getPostsUseCase = mock<GetPostsUseCase>()
    private val scheduler = Schedulers.trampoline()
    private lateinit var postsViewModel: PostsViewModel

    @Before
    fun setUp() {
        postsViewModel = PostsViewModel(getPostsUseCase, scheduler)
    }

    @Test
    fun getPosts_returnsError() {
        val throwable = Throwable("")
        given(getPostsUseCase.getPosts()).willReturn(Observable.error(throwable))

        postsViewModel.getPosts()

        assertEquals(throwable, postsViewModel.getErrorLiveData().value)
    }

    @Test
    fun getPosts_returnsPosts() {
        val postDomainList = listOf(mock<PostDomain>())
        given(getPostsUseCase.getPosts()).willReturn(Observable.just(postDomainList))

        postsViewModel.getPosts()

        assertEquals(postDomainList, postsViewModel.getPostsLiveData().value)
        assertEquals(1, postsViewModel.getCompletedLiveData().value)
    }
}