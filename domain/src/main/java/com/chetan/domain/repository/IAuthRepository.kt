package com.chetan.domain.repository

import com.chetan.domain.domainmodel.Result
import com.chetan.domain.domainmodel.User


interface IAuthRepository {


//    suspend fun setAuthStateListener(channel: SendChannel<Result<Exception, User?>>): Result<Exception, Unit>

    suspend fun getCurrentUser(): Result<Exception, User?>

    suspend fun signOutCurrentUser(): Result<Exception, Unit>

    suspend fun deleteCurrentUser(): Result<Exception, Boolean>

    suspend fun createGoogleUser(idToken: String): Result<Exception, Unit>

}