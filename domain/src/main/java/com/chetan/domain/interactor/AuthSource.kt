package com.chetan.domain.interactor

import com.chetan.domain.servicelocator.UserServiceLocator
import com.chetan.domain.domainmodel.Result
import com.chetan.domain.domainmodel.User

class AuthSource {

    suspend fun getCurrentUser(locator: UserServiceLocator):
            Result<Exception, User?> = locator.authRepository.getCurrentUser()

    suspend fun deleteCurrentUser(locator: UserServiceLocator):
            Result<Exception, Boolean> = locator.authRepository.deleteCurrentUser()

    suspend fun signOutCurrentUser(locator: UserServiceLocator):
            Result<Exception, Unit> = locator.authRepository.signOutCurrentUser()

    suspend fun createGoogleUser(idToken: String, locator: UserServiceLocator):
            Result<Exception, Unit> = locator.authRepository.createGoogleUser(idToken)

}