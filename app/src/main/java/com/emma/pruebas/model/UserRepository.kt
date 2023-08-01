package com.emma.pruebas.model

import com.emma.pruebas.data.UserResponse
import com.emma.pruebas.util.ResultHelper
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: UserService) {

    suspend fun getUser() : ResultHelper<UserResponse> = service.getUser()
}