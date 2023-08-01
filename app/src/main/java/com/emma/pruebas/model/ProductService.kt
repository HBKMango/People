package com.emma.pruebas.model

import com.emma.pruebas.api.ApiEndPoints
import com.emma.pruebas.data.Audit
import com.emma.pruebas.data.ObtenerMisAuditoriasPorFechaResult
import com.emma.pruebas.data.ProductsResponse
import com.emma.pruebas.database.OptionsDAO
import com.emma.pruebas.util.ResultHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService @Inject constructor(private val api: ApiEndPoints, private val db: OptionsDAO){

    suspend fun getProducts(): ResultHelper<ProductsResponse> {
        return withContext(Dispatchers.IO) {

            try {
                val result = api.getProducts()

                if (result.isSuccessful && result.body() != null) {
                    if (result.code() == 200) {
                        return@withContext ResultHelper.Success(result.body())
                    } else {
                        return@withContext ResultHelper.Error(result.errorBody()!!.string())
                    }
                } else {
                    return@withContext ResultHelper.Error(result.errorBody()!!.string())
                }
            } catch (e: Exception) {
                return@withContext ResultHelper.Exception("Error en el servicio")
            }
        }
    }

    suspend fun getData(): ResultHelper<Audit> {
        return withContext(Dispatchers.IO) {

            try {
                val result = api.getData()

                if (result.isSuccessful && result.body() != null) {
                    if (result.code() == 200) {
                        return@withContext ResultHelper.Success(result.body())
                    } else {
                        return@withContext ResultHelper.Error(result.errorBody()!!.string())
                    }
                } else {
                    return@withContext ResultHelper.Error(result.errorBody()!!.string())
                }
            } catch (e: Exception) {
                return@withContext ResultHelper.Exception("Error en el servicio")
            }
        }
    }

    suspend fun saveDataDB(list: List<ObtenerMisAuditoriasPorFechaResult>): ResultHelper<String> {
        return withContext(Dispatchers.IO) {
            try {
                db.deleteConfig()
                db.addData(list)
                return@withContext ResultHelper.Success("")
            } catch (e: Exception) {
                return@withContext ResultHelper.Exception("Error al consultar información")
            }
        }
    }


    suspend fun getDataDB(): ResultHelper<List<ObtenerMisAuditoriasPorFechaResult>> =
        withContext(Dispatchers.IO) {
            runCatching {
                ResultHelper.Success(db.getData())
            }.getOrElse {
                ResultHelper.Exception("Error al consultar información")
            }
        }
}