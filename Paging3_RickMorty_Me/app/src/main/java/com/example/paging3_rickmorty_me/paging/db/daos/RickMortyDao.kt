package com.example.paging3_rickmorty_me.paging.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3_rickmorty_me.model.Character
//import com.example.paging3_rickmorty_me.model.RickMortyResponse

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRickMorty(list: List<Character>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertArticleSingle(article: Article)

    @Query("SELECT * FROM rickandmorty_table ")
    fun getAllRickMorty(): PagingSource<Int, Character>

    @Query("DELETE FROM rickandmorty_table")
    suspend fun deleteAllRickMorty()
}