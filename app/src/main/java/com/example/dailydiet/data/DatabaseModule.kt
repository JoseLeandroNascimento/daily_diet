package com.example.dailydiet.data

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
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            name = "snack_db",
        )
            .fallbackToDestructiveMigration(true) // Somente em desenvolvimento
            .build()
    }

    @Provides
    fun provideSnackDao(database: AppDatabase): SnackDao {
        return database.getSnackDao()
    }
}