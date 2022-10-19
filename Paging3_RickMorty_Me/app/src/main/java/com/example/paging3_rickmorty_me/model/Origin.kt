package com.example.paging3_rickmorty_me.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Origin(
    @SerializedName("name")

    val nameOrigin: String,
    @SerializedName("url")

    val urlOrigin: String
) : Parcelable