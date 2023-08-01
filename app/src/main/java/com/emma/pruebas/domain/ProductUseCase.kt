package com.emma.pruebas.domain

import com.emma.pruebas.data.Audit
import com.emma.pruebas.data.ObtenerMisAuditoriasPorFechaResult
import com.emma.pruebas.data.ProductsResponse
import com.emma.pruebas.model.ProductsRepository
import com.emma.pruebas.util.ResultHelper
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val repository: ProductsRepository) {

    suspend fun getProducts(): ResultHelper<ProductsResponse> {
        return repository.getProducts()
    }

    suspend fun getAudit(): ResultHelper<Audit>{
        return repository.getAudit()
    }

    suspend fun saveDataDB(list: List<ObtenerMisAuditoriasPorFechaResult>): ResultHelper<String> {
        return repository.saveDataDB(list)
    }

    suspend fun getDataDB(): ResultHelper<List<ObtenerMisAuditoriasPorFechaResult>> {
        return repository.getDataDB()
    }
}