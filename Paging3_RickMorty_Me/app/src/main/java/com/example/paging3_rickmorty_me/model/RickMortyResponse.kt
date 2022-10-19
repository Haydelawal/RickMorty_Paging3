package com.example.paging3_rickmorty_me.model

import androidx.room.Embedded
import androidx.room.Entity
import com.example.paging3_rickmorty_me.Constants.RICKMORTY_TABLE
import com.example.paging3_rickmorty_me.model.Character
import com.example.paging3_rickmorty_me.model.Info

//@Entity(tableName = RICKMORTY_TABLE)
data class RickMortyResponse(
    val info: Info,
//    @Embedded
    val results: List<Character>
)