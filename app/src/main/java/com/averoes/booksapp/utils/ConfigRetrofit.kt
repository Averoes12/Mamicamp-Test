package com.averoes.booksapp.utils

import com.averoes.booksapp.model.ResponseBookDetail
import com.averoes.booksapp.model.book.ResponseBook
import com.averoes.booksapp.model.genre.Genre
import com.averoes.booksapp.model.writer.Writer
import com.averoes.booksapp.utils.Constant.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


class ConfigRetrofit {

    fun getInterceptor():OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("x-dreamfactory-api-key", "25e0bf00ab2fa7f03a9fa57035139e47ccb28c20658f6de907b8011347e369fb")
                    return@Interceptor chain.proceed(builder.build())
                })
        }.build()


    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Users::class.java)
}
interface Users {

    @GET("cabaca/_table/genre")
    fun getGenre(): Call<Genre?>

    @GET("book/category")
    fun getBookByCategory(@Query("id") bookId:Int):Call<ResponseBook>

    @GET("book/uptodate")
    fun getLatestBook(@Query("limit") limit:Int) : Call<ResponseBook>

    @GET("book/detail/{id}")
    fun getDetailBook(@Path("id") bookId:String):Call<ResponseBookDetail>

    @GET("writer/detail/57")
    fun getWriterDetail():Call<Writer>
}
