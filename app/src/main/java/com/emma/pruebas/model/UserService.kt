package com.emma.pruebas.model

import com.emma.pruebas.api.ApiEndPoints
import com.emma.pruebas.data.UserResponse
import com.emma.pruebas.util.ResultHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UserService @Inject constructor(private val api: ApiEndPoints) {

    suspend fun getUser(): ResultHelper<UserResponse> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val result = api.getUser()

                when (result.code()) {
                    200 -> {
                        ResultHelper.Success(result.body())
                    }

                    else -> {
                        ResultHelper.Error(result.errorBody()?.string() ?: "Error desconocido")
                    }
                }
            }.getOrElse {
                when (it) {
                    is IOException -> ResultHelper.Exception("Error de conexión")
                    else -> ResultHelper.Exception("Error en el servicio")
                }
            }
        }
    }
}