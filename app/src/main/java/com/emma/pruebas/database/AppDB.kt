package com.emma.pruebas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emma.pruebas.data.ObtenerMisAuditoriasPorFechaResult

@Database(entities = [ObtenerMisAuditoriasPorFechaResult::class], version = 1)
abstract class AppDB : RoomDatabase(){
    abstract fun appDAO(): OptionsDAO
}