package com.example.paging3_rickmorty_me.paging.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.paging3_rickmorty_me.model.Character
//import com.example.paging3_rickmorty_me.model.RickMortyResponse
import com.example.paging3_rickmorty_me.paging.db.daos.RickMortyDao
import com.example.paging3_rickmorty_me.paging.db.daos.RickMortyRemoteKeysDao
import com.example.paging3_rickmorty_me.paging.db.model.RickMortyRemoteKey
import com.example.paging3_rickmorty_me.utils.MyTypeConverter

@Database(entities = [Character::class, RickMortyRemoteKey::class], version = 1)
@TypeConverters(MyTypeConverter::class)
abstract class RickMortyDatabase : RoomDatabase() {

    abstract fun rickMortyDao(): RickMortyDao
    abstract fun rickMortyRemoteKeysDao(): RickMortyRemoteKeysDao

}