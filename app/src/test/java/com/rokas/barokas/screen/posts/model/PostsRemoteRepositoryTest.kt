package com.rokas.barokas.screen.posts.model

import com.rokas.barokas.component.network.PostsService
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class PostsRemoteRepositoryTest {
    private val postsService = mock<PostsService>()
    private lateinit var postsRemoteRepository: PostsRemoteRepository

    @Before
    fun setUp() {
        postsRemoteRepository = PostsRemoteRepository(postsService)
    }

    @Test
    fun testGetPosts_returnsListOfPostDomains() {
        val postResponse = mock<PostResponse>()
        val postResponse2 = mock<PostResponse>()
        val postResponse3 = mock<PostResponse>()

        val postDomain = mock<PostDomain>()
        val postDomain2 = mock<PostDomain>()
        val postDomain3 = mock<PostDomain>()

        given(postResponse.toDomain()).willReturn(postDomain)
        given(postResponse2.toDomain()).willReturn(postDomain2)
        given(postResponse3.toDomain()).willReturn(postDomain3)

        given(postsService.getPosts()).willReturn(
            Single.just(listOf(postResponse, postResponse2, postResponse3))
        )

        val testObserver = postsRemoteRepository.getPosts().test()

        testObserver.assertValue(listOf(postDomain, postDomain2, postDomain3))
        testObserver.assertComplete()
    }
}