package com.emma.pruebas.model

import com.emma.pruebas.data.Audit
import com.emma.pruebas.data.ObtenerMisAuditoriasPorFechaResult
import com.emma.pruebas.data.ProductsResponse
import com.emma.pruebas.util.ResultHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val service: ProductService) {

    suspend fun getProducts(): ResultHelper<ProductsResponse> {
        return service.getProducts()
    }

    suspend fun getAudit(): ResultHelper<Audit>{
        return service.getData()
    }

    suspend fun saveDataDB(list: List<ObtenerMisAuditoriasPorFechaResult>) : ResultHelper<String> {
        return service.saveDataDB(list)
    }

    suspend fun getDataDB(): ResultHelper<List<ObtenerMisAuditoriasPorFechaResult>> {
        return service.getDataDB()
    }
}