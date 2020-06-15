package com.averoes.booksapp.model.book


import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("book_official")
    val bookOfficial: Double,
    @SerializedName("chapter")
    val chapter: Double,
    @SerializedName("chapter_book")
    val chapterBook: Double,
    @SerializedName("chapter_new")
    val chapterNew: Double,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("user")
    val user: Double,
    @SerializedName("viewer")
    val viewer: Double
)