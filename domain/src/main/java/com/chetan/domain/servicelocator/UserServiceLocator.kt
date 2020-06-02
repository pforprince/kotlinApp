package com.chetan.domain.servicelocator

import com.chetan.domain.repository.IAuthRepository

class UserServiceLocator(val authRepository: IAuthRepository)