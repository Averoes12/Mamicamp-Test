package com.averoes.booksapp.model.book


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("book_id")
    val bookId: Int,
    @SerializedName("category")
    val category: Any?,
    @SerializedName("chapter_count")
    val chapterCount: Int,
    @SerializedName("cover_url")
    val coverUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isNew")
    val isNew: Boolean,
    @SerializedName("is_update")
    val isUpdate: Boolean,
    @SerializedName("rate_sum")
    val rateSum: Double,
    @SerializedName("schedule_task")
    val scheduleTask: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("view_count")
    val viewCount: Int,
    @SerializedName("Writer_by_writer_id")
    val writerByWriterId: WriterByWriterId,
    @SerializedName("writer_id")
    val writerId: Int
)