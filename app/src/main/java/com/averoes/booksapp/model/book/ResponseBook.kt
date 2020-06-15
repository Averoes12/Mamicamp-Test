package com.averoes.booksapp.model.book


import com.google.gson.annotations.SerializedName

data class ResponseBook(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("time")
    val time: Time
)