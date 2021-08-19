package com.rokas.barokas.screen.posts.model

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class PostsLocalRepositoryTest {
    private val postsDao = mock<PostsDao>()
    private val scheduler = TestScheduler()
    private lateinit var postsLocalRepository: PostsLocalRepository

    @Before
    fun setUp() {
        postsLocalRepository = PostsLocalRepository(postsDao, scheduler)
    }

    @Test
    fun testInsertAll_insertsAllPostsToDao() {
        val postDomain = mock<PostDomain>()
        val postDomain2 = mock<PostDomain>()
        val postDomain3 = mock<PostDomain>()

        val postEntity = mock<PostEntity>()
        val postEntity2 = mock<PostEntity>()
        val postEntity3 = mock<PostEntity>()

        given(postDomain.toEntity()).willReturn(postEntity)
        given(postDomain2.toEntity()).willReturn(postEntity2)
        given(postDomain3.toEntity()).willReturn(postEntity3)

        val domainPosts = listOf(postDomain, postDomain2, postDomain3)
        val entityPosts = listOf(postEntity, postEntity2, postEntity3)

        given(postsDao.insertAllPosts(entityPosts))
            .willReturn(Completable.complete())

        val testObserver = postsLocalRepository.insertAll(domainPosts).test()
        scheduler.triggerActions()

        verify(postsDao).insertAllPosts(entityPosts)
        testObserver.assertComplete()
    }

    @Test
    fun testGetPosts_returnsPostsFromDao() {
        val postDomain = mock<PostDomain>()
        val postDomain2 = mock<PostDomain>()
        val postDomain3 = mock<PostDomain>()

        val postEntity = mock<PostEntity>()
        val postEntity2 = mock<PostEntity>()
        val postEntity3 = mock<PostEntity>()

        given(postEntity.toDomain()).willReturn(postDomain)
        given(postEntity2.toDomain()).willReturn(postDomain2)
        given(postEntity3.toDomain()).willReturn(postDomain3)

        val entityPosts = listOf(postEntity, postEntity2, postEntity3)
        val domainPosts = listOf(postDomain, postDomain2, postDomain3)

        given(postEntity.toDomain()).willReturn(postDomain)
        given(postsDao.getPosts()).willReturn(Observable.just(entityPosts))

        val testObserver = postsLocalRepository.getPosts().test()
        scheduler.triggerActions()

        testObserver.assertValue(domainPosts)
        testObserver.assertComplete()
    }

    @Test
    fun testGetPost_returnsPostFromDao() {
        val postEntity = mock<PostEntity>()
        val postDomain = mock<PostDomain>()
        given(postEntity.toDomain()).willReturn(postDomain)
        given(postsDao.getPost(POST_ID)).willReturn(Single.just(postEntity))

        val testObserver = postsLocalRepository.getPost(POST_ID).test()
        scheduler.triggerActions()

        testObserver.assertValue(postDomain)
        testObserver.assertComplete()
    }

    companion object {
        private const val POST_ID = 1
    }
}