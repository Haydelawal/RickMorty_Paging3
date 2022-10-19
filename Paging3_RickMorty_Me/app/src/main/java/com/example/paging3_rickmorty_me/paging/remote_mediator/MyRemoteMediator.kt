package com.example.paging3_rickmorty_me.paging.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging3_rickmorty_me.api.Api
import com.example.paging3_rickmorty_me.model.Character
import com.example.paging3_rickmorty_me.paging.db.RickMortyDatabase
import com.example.paging3_rickmorty_me.paging.db.model.RickMortyRemoteKey

@ExperimentalPagingApi
class MyRemoteMediator(
    private val api: Api,
    private val rickMortyDatabase: RickMortyDatabase
) : RemoteMediator<Int, Character>()
{

    private val rickMortyDao = rickMortyDatabase.rickMortyDao()
    private val rickMortyRemoteKeysDao = rickMortyDatabase.rickMortyRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = api.getAllCharacters(1)
            val endOfPaginationReached = response.body()?.results?.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached == true) null else currentPage + 1

            rickMortyDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    rickMortyDao.deleteAllRickMorty()
                    rickMortyRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.body()?.results
                    ?.map { restApiResponse ->
                        RickMortyRemoteKey(
                            id = restApiResponse.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                rickMortyRemoteKeysDao.insertAllRemoteKeys(list =  keys!!)
                response.body()?.let { rickMortyDao.insertRickMorty(list = it.results) }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached!!)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): RickMortyRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                rickMortyRemoteKeysDao.getAllRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Character>
    ): RickMortyRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { apiResponse ->
                rickMortyRemoteKeysDao.getAllRemoteKeys(id = apiResponse.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Character>
    ): RickMortyRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { apiResponse ->
                rickMortyRemoteKeysDao.getAllRemoteKeys(id = apiResponse.id)
            }
    }
}