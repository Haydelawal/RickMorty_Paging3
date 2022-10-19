package com.example.paging3_rickmorty_me.api

import com.example.paging3_rickmorty_me.Constants.END_POINT
import com.example.paging3_rickmorty_me.model.Character
import com.example.paging3_rickmorty_me.model.RickMortyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

            @GET(END_POINT)
            suspend fun getAllCharacters(
                @Query("page") page: Int
            ): Response<RickMortyResponse>
}