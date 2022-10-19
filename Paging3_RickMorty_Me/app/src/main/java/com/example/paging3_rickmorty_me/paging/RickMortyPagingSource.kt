package com.example.paging3_rickmorty_me.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3_rickmorty_me.api.Api
import com.example.paging3_rickmorty_me.model.Character
//import com.example.paging3_rickmorty_me.model.RickMortyResponse
import retrofit2.HttpException
import java.io.IOException

class RickMortyPagingSource
    (private val api: Api) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {

            val position =
                if (params.key == null) {
                    1
                } else {
                    params.key
                }

            val currentPage = params.key ?: 1
            val response = api.getAllCharacters(currentPage)
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<Character>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (position == 1) null else position!! - 1,
                nextKey = currentPage.plus(1)
            )




        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}
