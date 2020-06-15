package com.averoes.booksapp.model.book


import com.google.gson.annotations.SerializedName

data class UserByUserId(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)