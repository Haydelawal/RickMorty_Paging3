package com.example.paging3_rickmorty_me.paging.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3_rickmorty_me.model.Character
import com.example.paging3_rickmorty_me.paging.db.model.RickMortyRemoteKey

@Dao
interface RickMortyRemoteKeysDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(list: List<RickMortyRemoteKey>)

    @Query("SELECT * FROM rickandmorty_remote_keys_table WHERE id = :id")
    suspend fun getAllRemoteKeys(id: Int): RickMortyRemoteKey?

    @Query("DELETE FROM rickandmorty_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}