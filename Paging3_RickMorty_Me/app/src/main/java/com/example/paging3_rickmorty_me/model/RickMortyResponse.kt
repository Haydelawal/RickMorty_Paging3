package com.example.paging3_rickmorty_me.model

import com.example.paging3_rickmorty_me.model.Character
import com.example.paging3_rickmorty_me.model.Info

data class RickMortyResponse(
    val info: Info,
    val results: List<Character>
)