package com.rokas.barokas.screen.details.component

import com.rokas.barokas.screen.posts.model.PostDomain
import com.rokas.barokas.screen.posts.model.PostsLocalRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GetPostUseCaseTest {
    private val postsLocalRepository = mock<PostsLocalRepository>()
    private lateinit var getPostUseCase: GetPostUseCase

    @Before
    fun setUp() {
        getPostUseCase = GetPostUseCase(postsLocalRepository)
    }

    @Test
    fun testGetPost_returnsPostDomain() {
        val postDomain = mock<PostDomain>()
        given(postsLocalRepository.getPost(POST_ID)).willReturn(Single.just(postDomain))

        val testObserver = getPostUseCase.getPost(POST_ID).test()

        testObserver.assertValue(postDomain)
        testObserver.assertComplete()
    }

    companion object {
        private const val POST_ID = 1
    }
}