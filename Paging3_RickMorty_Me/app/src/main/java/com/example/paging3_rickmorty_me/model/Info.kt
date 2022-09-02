package com.example.paging3_rickmorty_me.model

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String?
)