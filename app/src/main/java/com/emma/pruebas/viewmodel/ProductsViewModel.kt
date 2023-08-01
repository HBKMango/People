package com.emma.pruebas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emma.pruebas.data.Audits
import com.emma.pruebas.data.Products
import com.emma.pruebas.domain.ProductUseCase
import com.emma.pruebas.util.ResultHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel() {

    val resultProducts = MutableStateFlow<Products>(Products.Empty)
    val resultAudit = MutableStateFlow<Audits>(Audits.Empty)
    val resultAuditDB = MutableStateFlow<Audits>(Audits.Empty)

    fun getProducts() {
        viewModelScope.launch {
            val result = useCase.getProducts()

            when (result) {

                is ResultHelper.Success -> {
                    result.data?.let {
                        resultProducts.value = Products.Master(it)
                        resultProducts.value = Products.Empty
                    }
                }

                is ResultHelper.Error -> {
                    resultProducts.value = Products.Error(result.error)
                }

                is ResultHelper.Exception -> {
                    resultProducts.value =
                        Products.Exception(result.exception)
                }
            }
        }
    }

    fun getAudit() {
        viewModelScope.launch {
            val result = useCase.getAudit()

            when (result) {

                is ResultHelper.Success -> {
                    result.data?.let {
                        val second = useCase.saveDataDB(it.obtenerMisAuditoriasPorFechaResult)

                        when (second) {
                            is ResultHelper.Success -> {
                                val getData = useCase.getDataDB()

                                when (getData) {
                                    is ResultHelper.Success -> {
                                        resultAuditDB.value = Audits.Master(it)
                                    }

                                    is ResultHelper.Error -> {
                                        resultAuditDB.value = Audits.Error(getData.error)
                                    }

                                    is ResultHelper.Exception -> {
                                        resultAuditDB.value = Audits.Exception(getData.exception)
                                    }
                                }
                            }

                            is ResultHelper.Error -> {
                                resultAudit.value = Audits.Error(second.error)
                            }

                            is ResultHelper.Exception -> {
                                resultAudit.value = Audits.Exception(second.exception)
                            }
                        }
                        //resultAudit.value = Audits.Master(it)
                        //resultAudit.value = Audits.Empty
                    }
                }

                is ResultHelper.Error -> {
                    resultAudit.value = Audits.Error(result.error)
                }

                is ResultHelper.Exception -> {
                    resultAudit.value = Audits.Exception(result.exception)
                }
            }
        }
    }
}