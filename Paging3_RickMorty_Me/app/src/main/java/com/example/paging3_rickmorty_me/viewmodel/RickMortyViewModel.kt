package com.example.paging3_rickmorty_me.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging3_rickmorty_me.api.Api
import com.example.paging3_rickmorty_me.paging.RickMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(
    private val api: Api
) : ViewModel() {

    val listData = Pager(
        PagingConfig(
            pageSize = 1,
            enablePlaceholders = false
        )
    ) {
        RickMortyPagingSource(api)
    }
        .flow
        .cachedIn(viewModelScope)
}