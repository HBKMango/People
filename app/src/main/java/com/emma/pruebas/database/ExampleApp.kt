package com.emma.pruebas.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExampleApp {

    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext appContext: Context): AppDB {
        return Room.databaseBuilder(appContext, AppDB::class.java, "DataBase").build()
    }

    @Singleton
    @Provides
    fun provideDao(db: AppDB) = db.appDAO()
}