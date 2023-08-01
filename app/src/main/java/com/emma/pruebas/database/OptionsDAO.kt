package com.emma.pruebas.database

import androidx.room.*
import com.emma.pruebas.data.ObtenerMisAuditoriasPorFechaResult

@Dao
interface OptionsDAO {

    @Query("SELECT * FROM ObtenerMisAuditoriasPorFechaResult")
    suspend fun getData(): List<ObtenerMisAuditoriasPorFechaResult>

    @Insert
    suspend fun addData(list : List<ObtenerMisAuditoriasPorFechaResult>)

    @Query("DELETE FROM ObtenerMisAuditoriasPorFechaResult")
    suspend fun deleteConfig()
}