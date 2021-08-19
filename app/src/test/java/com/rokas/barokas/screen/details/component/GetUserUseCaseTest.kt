package com.rokas.barokas.screen.details.component

import com.rokas.barokas.screen.details.model.UserDomain
import com.rokas.barokas.screen.details.model.UsersLocalRepository
import com.rokas.barokas.screen.details.model.UsersRemoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GetUserUseCaseTest {
    private val usersRemoteRepository = mock<UsersRemoteRepository>()
    private val usersLocalRepository = mock<UsersLocalRepository>()
    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        getUserUseCase = GetUserUseCase(usersRemoteRepository, usersLocalRepository)
    }

    @Test
    fun testGetUser_noNetwork_returnsLocalData() {
        val localUser = mock<UserDomain>()
        val remoteError = mock<Throwable>()

        given(usersLocalRepository.getUser(USER_ID)).willReturn(Observable.just(localUser))
        given(usersRemoteRepository.getUser(USER_ID)).willReturn(Single.error(remoteError))

        val testObserver = getUserUseCase.getUser(USER_ID).test()

        testObserver.assertValue(localUser)
        testObserver.assertError(remoteError)
    }

    @Test
    fun testGetUser_noLocal_returnsLocalData() {
        val localError = mock<Throwable>()
        val remoteUser = mock<UserDomain>()

        given(usersLocalRepository.getUser(USER_ID)).willReturn(Observable.error(localError))
        given(usersRemoteRepository.getUser(USER_ID)).willReturn(Single.just(remoteUser))
        given(usersLocalRepository.insert(remoteUser)).willReturn(Completable.complete())

        val testObserver = getUserUseCase.getUser(USER_ID).test()

        testObserver.assertValue(remoteUser)
        testObserver.assertComplete()
    }

    @Test
    fun testGetUser_remoteAvailableAndLocalAvailable_returnsLocalAndRemoteData() {
        val localUser = mock<UserDomain>()
        val remoteUser = mock<UserDomain>()

        given(usersLocalRepository.getUser(USER_ID)).willReturn(Observable.just(localUser))
        given(usersRemoteRepository.getUser(USER_ID)).willReturn(Single.just(remoteUser))
        given(usersLocalRepository.insert(remoteUser)).willReturn(Completable.complete())

        val testObserver = getUserUseCase.getUser(USER_ID).test()

        testObserver.assertValues(localUser, remoteUser)
        testObserver.assertComplete()
    }

    companion object {
        private const val USER_ID = 1
    }
}