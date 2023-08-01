package com.emma.pruebas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emma.pruebas.data.User
import com.emma.pruebas.domain.UserUseCase
import com.emma.pruebas.util.ResultHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: UserUseCase) : ViewModel() {

    val resultUser = MutableStateFlow<User>(User.Empty)

    fun getProducts() {
        viewModelScope.launch {
            val result = useCase.getUser()

            when (result) {

                is ResultHelper.Success -> {
                    result.data?.let {
                        resultUser.value = User.Master(it)
                        resultUser.value = User.Empty
                    }
                }

                is ResultHelper.Error -> {
                    resultUser.value = User.Error(result.error)
                }

                is ResultHelper.Exception -> {
                    resultUser.value =
                        User.Exception(result.exception)
                }
            }
        }
    }
}