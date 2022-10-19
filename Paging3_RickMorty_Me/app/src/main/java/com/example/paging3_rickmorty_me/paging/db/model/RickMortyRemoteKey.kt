package com.example.paging3_rickmorty_me.paging.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3_rickmorty_me.Constants.RICKMORTY_KEYS_TABLE

@Entity(tableName = RICKMORTY_KEYS_TABLE)
data class RickMortyRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)