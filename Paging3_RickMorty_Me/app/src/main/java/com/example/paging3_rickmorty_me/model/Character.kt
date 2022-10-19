package com.example.paging3_rickmorty_me.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3_rickmorty_me.Constants
import com.example.paging3_rickmorty_me.Constants.RICKMORTY_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = RICKMORTY_TABLE)
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    @Embedded
    val location: Location,
    val name: String,
    @Embedded
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    @SerializedName("url")
    val urlCharacter: String
) : Parcelable