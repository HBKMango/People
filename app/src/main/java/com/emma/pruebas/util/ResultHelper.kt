package com.emma.pruebas.util


sealed class ResultHelper<out T> {
    data class Success<out T>(val data: T?) : ResultHelper<T>()
    data class Error(val error: String) : ResultHelper<Nothing>()
    data class Exception(val exception: String) : ResultHelper<Nothing>()
}