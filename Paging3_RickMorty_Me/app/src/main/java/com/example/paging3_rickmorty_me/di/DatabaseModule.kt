package com.example.paging3_rickmorty_me.di

import android.content.Context
import androidx.room.Room
import com.example.paging3_rickmorty_me.Constants.RICKMORTY_DATABASE
import com.example.paging3_rickmorty_me.paging.db.RickMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RickMortyDatabase {
        return Room.databaseBuilder(
            context,
            RickMortyDatabase::class.java,
            RICKMORTY_DATABASE
        ).build()
    }


    @Provides
    fun provideRickMortyDao(database: RickMortyDatabase) = database.rickMortyDao()

    @Provides
    fun provideRickMortyRemoteKeysDao(database: RickMortyDatabase) =
        database.rickMortyRemoteKeysDao()



}