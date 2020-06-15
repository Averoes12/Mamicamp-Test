package com.averoes.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.booksapp.model.book.ResponseBook
import com.averoes.booksapp.ui.home.BooksAdapter
import com.averoes.booksapp.utils.ConfigRetrofit
import com.averoes.booksapp.utils.Constant.GENRE_ID
import com.averoes.booksapp.utils.Constant.NAME_GENRE
import kotlinx.android.synthetic.main.activity_genre_activty.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.empty_state
import kotlinx.android.synthetic.main.fragment_home.loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_activty)

        getBooksByCategory(intent.getIntExtra(GENRE_ID, 0))
        supportActionBar?.title = intent.getStringExtra(NAME_GENRE)
    }

    private fun getBooksByCategory(genreId: Int) {
        enableLoading()
        hideEmptyState()
        ConfigRetrofit().getService().getBookByCategory(genreId)
            .enqueue(object : Callback<ResponseBook> {
                override fun onFailure(call: Call<ResponseBook>, t: Throwable) {
                    disabledLoading()
                    showEmptyState()
                    Log.d("RESPONSE", "Error ${t.localizedMessage}")
                }

                override fun onResponse(
                    call: Call<ResponseBook>,
                    response: Response<ResponseBook>
                ) {
                    Log.d("Response", response.body().toString())
                    book_genre_list.adapter = response.body()?.let { BooksAdapter(it) }
                    book_genre_list.layoutManager =
                        GridLayoutManager(this@GenreActivty, 2, GridLayoutManager.VERTICAL, false)
                    disabledLoading()
                    hideEmptyState()
                }

            })
    }

    private fun enableLoading() {
        loading.visibility = View.VISIBLE
        content_container.visibility = View.GONE
    }

    private fun disabledLoading() {
        loading.visibility = View.GONE
        content_container.visibility = View.VISIBLE
    }

    private fun showEmptyState() {
        empty_state.visibility = View.VISIBLE
        content_container.visibility = View.GONE
    }

    private fun hideEmptyState(){
        content_container.visibility = View.VISIBLE
        empty_state.visibility = View.GONE
    }

}