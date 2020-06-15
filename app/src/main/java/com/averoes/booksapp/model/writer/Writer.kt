package com.averoes.booksapp.model.writer

import com.google.gson.annotations.SerializedName

data class Writer(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class KaryaItem(

	@field:SerializedName("Writer_by_writer_id")
	val writerByWriterId: WriterByWriterId? = null,

	@field:SerializedName("cover_url")
	val coverUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("isNew")
	val isNew: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("schedule_task")
	val scheduleTask: String? = null,

	@field:SerializedName("is_update")
	val isUpdate: Boolean? = null,

	@field:SerializedName("bab_count")
	val babCount: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("rate_sum")
	val rateSum: Double? = null,

	@field:SerializedName("writer_id")
	val writerId: Int? = null,

	@field:SerializedName("view_count")
	val viewCount: Int? = null,

	@field:SerializedName("chapter_count")
	val chapterCount: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class WriterByWriterId(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("User_by_user_id")
	val userByUserId: UserByUserId? = null,

	@field:SerializedName("royalty_id")
	val royaltyId: Int? = null,

	@field:SerializedName("status")
	val status: Any? = null
)

data class UserByUserId(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class WriterByUserId(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("royalty_id")
	val royaltyId: Int? = null,

	@field:SerializedName("status")
	val status: Any? = null
)

data class Result(

	@field:SerializedName("birthday")
	val birthday: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("count_following")
	val countFollowing: Int? = null,

	@field:SerializedName("current_level")
	val currentLevel: Int? = null,

	@field:SerializedName("Writer_by_user_id")
	val writerByUserId: WriterByUserId? = null,

	@field:SerializedName("reading_list")
	val readingList: List<Any?>? = null,

	@field:SerializedName("writer_level")
	val writerLevel: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("karya")
	val karya: List<KaryaItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("following_user")
	val followingUser: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("photo_url")
	val photoUrl: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("writer_level_name")
	val writerLevelName: String? = null,

	@field:SerializedName("writer_id")
	val writerId: Int? = null,

	@field:SerializedName("count_follower")
	val countFollower: Int? = null,

	@field:SerializedName("is_following")
	val isFollowing: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("link_user")
	val linkUser: String? = null,

	@field:SerializedName("coin")
	val coin: Int? = null
)
