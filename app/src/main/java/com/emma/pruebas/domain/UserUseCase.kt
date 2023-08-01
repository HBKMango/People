package com.emma.pruebas.domain

import com.emma.pruebas.data.UserResponse
import com.emma.pruebas.model.UserRepository
import com.emma.pruebas.util.ResultHelper
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun getUser(): ResultHelper<UserResponse> = repository.getUser()
}