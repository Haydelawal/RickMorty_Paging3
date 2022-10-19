package com.example.paging3_rickmorty_me.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3_rickmorty_me.Constants.ITEMS_PER_PAGE
import com.example.paging3_rickmorty_me.api.Api
import com.example.paging3_rickmorty_me.model.Character
//import com.example.paging3_rickmorty_me.model.RickMortyResponse
import com.example.paging3_rickmorty_me.paging.db.RickMortyDatabase
import com.example.paging3_rickmorty_me.paging.remote_mediator.MyRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class MyRepository @Inject constructor(
    private val api: Api,
    private val rickMortyDatabase: RickMortyDatabase
) {

    fun getAllRickMorty(): Flow<PagingData<Character>> {
        val pagingSourceFactory = { rickMortyDatabase.rickMortyDao().getAllRickMorty() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = MyRemoteMediator(
                api = api,
                rickMortyDatabase = rickMortyDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}