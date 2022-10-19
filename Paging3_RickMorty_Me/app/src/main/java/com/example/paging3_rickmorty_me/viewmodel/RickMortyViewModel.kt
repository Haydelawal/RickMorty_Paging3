package com.example.paging3_rickmorty_me.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging3_rickmorty_me.api.Api
import com.example.paging3_rickmorty_me.paging.RickMortyPagingSource
import com.example.paging3_rickmorty_me.repo.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
//    private val api: Api
//) : ViewModel() {
//
//    val listData = Pager(
//        PagingConfig(
//            pageSize = 1,
//            enablePlaceholders = false
//        )
//    ) {
//        RickMortyPagingSource(api)
//    }
//        .flow
//        .cachedIn(viewModelScope)
//}
    repository: MyRepository
): ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val getAllRickMorty = repository.getAllRickMorty()
}