package com.averoes.booksapp.model.book


import com.averoes.booksapp.model.book.UserByUserId
import com.google.gson.annotations.SerializedName

data class WriterByWriterId(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kelas")
    val kelas: String?,
    @SerializedName("royalty_id")
    val royaltyId: Any?,
    @SerializedName("schedule_task")
    val scheduleTask: String,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("User_by_user_id")
    val userByUserId: UserByUserId,
    @SerializedName("user_id")
    val userId: Int
)