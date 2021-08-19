package com.rokas.barokas.screen.details.model

import com.rokas.barokas.component.network.UsersService
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class UsersRemoteRepositoryTest {
    private val usersService = mock<UsersService>()
    private lateinit var usersRemoteRepository: UsersRemoteRepository

    @Before
    fun setUp() {
        usersRemoteRepository = UsersRemoteRepository(usersService)
    }

    @Test
    fun testGetUser_returnsUserDomain() {
        val userResponse = mock<UserResponse>()
        val userDomain = mock<UserDomain>()
        given(userResponse.toDomain()).willReturn(userDomain)
        given(usersService.getUser(USER_ID)).willReturn(Single.just(userResponse))

        val testObserver = usersRemoteRepository.getUser(USER_ID).test()

        testObserver.assertValue(userDomain)
        testObserver.assertComplete()
    }

    companion object {
        private const val USER_ID = 1
    }
}