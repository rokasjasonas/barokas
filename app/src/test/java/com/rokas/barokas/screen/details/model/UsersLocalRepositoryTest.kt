package com.rokas.barokas.screen.details.model

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class UsersLocalRepositoryTest {
    private val usersDao = mock<UsersDao>()
    private val scheduler = TestScheduler()
    private lateinit var usersLocalRepository: UsersLocalRepository

    @Before
    fun setUp() {
        usersLocalRepository = UsersLocalRepository(usersDao, scheduler)
    }

    @Test
    fun testInsert_insertsUserToDao() {
        val userDomain = mock<UserDomain>()
        val userEntity = mock<UserEntity>()
        given(userDomain.toEntity()).willReturn(userEntity)
        given(usersDao.insertUser(userEntity)).willReturn(Completable.complete())

        val testObserver = usersLocalRepository.insert(userDomain).test()
        scheduler.triggerActions()

        verify(usersDao).insertUser(userEntity)
        testObserver.assertComplete()
    }

    @Test
    fun testGetUser_returnsUserFromDao() {
        val userEntity = mock<UserEntity>()
        val userDomain = mock<UserDomain>()
        given(userEntity.toDomain()).willReturn(userDomain)
        given(usersDao.getUser(USER_ID)).willReturn(Single.just(userEntity))

        val testObserver = usersLocalRepository.getUser(USER_ID).test()
        scheduler.triggerActions()

        testObserver.assertValue(userDomain)
        testObserver.assertComplete()
    }

    companion object {
        private const val USER_ID = 1
    }
}