package com.rokas.barokas.screen.posts.component

import com.rokas.barokas.screen.posts.model.PostDomain
import com.rokas.barokas.screen.posts.model.PostsLocalRepository
import com.rokas.barokas.screen.posts.model.PostsRemoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GetPostsUseCaseTest {
    private val postsRemoteRepository = mock<PostsRemoteRepository>()
    private val postsLocalRepository = mock<PostsLocalRepository>()
    private lateinit var getPostsUseCase: GetPostsUseCase

    @Before
    fun setUp() {
        getPostsUseCase = GetPostsUseCase(postsRemoteRepository, postsLocalRepository)
    }

    @Test
    fun testGetPosts_noNetwork_returnsLocalData() {
        val localPosts = listOf(mock<PostDomain>())
        val remoteError = mock<Throwable>()

        given(postsLocalRepository.getPosts()).willReturn(Observable.just(localPosts))
        given(postsRemoteRepository.getPosts()).willReturn(Single.error(remoteError))

        val testObserver = getPostsUseCase.getPosts().test()

        testObserver.assertValue(localPosts)
        testObserver.assertError(remoteError)
    }

    @Test
    fun testGetPosts_noLocal_returnsLocalData() {
        val localPosts = listOf<PostDomain>()
        val remotePosts = listOf(mock<PostDomain>())

        given(postsLocalRepository.getPosts()).willReturn(Observable.just(localPosts))
        given(postsRemoteRepository.getPosts()).willReturn(Single.just(remotePosts))
        given(postsLocalRepository.insertAll(remotePosts)).willReturn(Completable.complete())

        val testObserver = getPostsUseCase.getPosts().test()

        testObserver.assertValues(localPosts, remotePosts)
        testObserver.assertComplete()
    }

    @Test
    fun testGetPosts_remoteAvailableAndLocalAvailable_returnsLocalAndRemoteData() {
        val localPosts = listOf(mock<PostDomain>())
        val remotePosts = listOf(mock<PostDomain>())

        given(postsLocalRepository.getPosts()).willReturn(Observable.just(localPosts))
        given(postsRemoteRepository.getPosts()).willReturn(Single.just(remotePosts))
        given(postsLocalRepository.insertAll(remotePosts)).willReturn(Completable.complete())

        val testObserver = getPostsUseCase.getPosts().test()

        testObserver.assertValues(localPosts, remotePosts)
        testObserver.assertComplete()
    }
}