package com.rokas.barokas.screen.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rokas.barokas.screen.details.component.GetPostUseCase
import com.rokas.barokas.screen.details.component.GetUserUseCase
import com.rokas.barokas.screen.details.model.UserDomain
import com.rokas.barokas.screen.posts.model.PostDomain
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
class DetailsViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getPostUseCase = mock<GetPostUseCase>()
    private val getUserUseCase = mock<GetUserUseCase>()
    private val scheduler = Schedulers.trampoline()
    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(getPostUseCase, getUserUseCase, scheduler)
    }

    @Test
    fun getScreenDetails_returnsPostError() {
        val throwable = Throwable("")
        given(getPostUseCase.getPost(POST_ID)).willReturn(Single.error(throwable))

        detailsViewModel.getScreenDetails(POST_ID)

        assertEquals(throwable, detailsViewModel.getErrorLiveData().value)
        assertTrue(detailsViewModel.getPostLiveData().value == null)
        assertTrue(detailsViewModel.getUserLiveData().value == null)
    }

    @Test
    fun getScreenDetails_returnsUserError() {
        val throwable = Throwable("")
        val postDomain = mock<PostDomain>()
        given(postDomain.userId).willReturn(USER_ID)
        given(getPostUseCase.getPost(POST_ID)).willReturn(Single.just(postDomain))
        given(getUserUseCase.getUser(USER_ID)).willReturn(Observable.error(throwable))

        detailsViewModel.getScreenDetails(POST_ID)

        assertEquals(throwable, detailsViewModel.getErrorLiveData().value)
        assertEquals(postDomain, detailsViewModel.getPostLiveData().value)
        assertTrue(detailsViewModel.getUserLiveData().value == null)
    }

    @Test
    fun getScreenDetails_returnsSuccess() {
        val postDomain = mock<PostDomain>()
        val userDomain = mock<UserDomain>()
        given(postDomain.userId).willReturn(USER_ID)
        given(getPostUseCase.getPost(POST_ID)).willReturn(Single.just(postDomain))
        given(getUserUseCase.getUser(USER_ID)).willReturn(Observable.just(userDomain))

        detailsViewModel.getScreenDetails(POST_ID)

        assertTrue(detailsViewModel.getErrorLiveData().value == null)
        assertEquals(postDomain, detailsViewModel.getPostLiveData().value)
        assertEquals(userDomain, detailsViewModel.getUserLiveData().value)
    }

    companion object {
        private const val POST_ID = 1
        private const val USER_ID = 2
    }
}