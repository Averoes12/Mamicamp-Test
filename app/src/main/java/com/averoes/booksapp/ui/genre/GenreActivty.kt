package com.averoes.booksapp.ui.genre

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.averoes.booksapp.R
import com.averoes.booksapp.model.book.ResponseBook
import com.averoes.booksapp.ui.home.BooksAdapter
import com.averoes.booksapp.ui.home.GenreAdapter
import com.averoes.booksapp.utils.ConfigRetrofit
import com.averoes.booksapp.utils.Constant.GENRE_ID
import com.averoes.booksapp.utils.Constant.NAME_GENRE
import com.github.ybq.android.spinkit.style.FoldingCube
import kotlinx.android.synthetic.main.activity_genre_activty.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreActivty : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_activty)

        getBooksByCategory(intent.getIntExtra(GENRE_ID, 0))
        supportActionBar!!.hide()

        title_toolbar.text = intent.getStringExtra(NAME_GENRE)
        icon_back.setOnClickListener {
            finish()
        }

        val animation = FoldingCube();
        loading_genre.setIndeterminateDrawable(animation);

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
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
        loading_genre.visibility = View.VISIBLE
        content_container.visibility = View.GONE
    }

    private fun disabledLoading() {
        loading_genre.visibility = View.GONE
        content_container.visibility = View.VISIBLE
    }

    private fun showEmptyState() {
        empty_genre.visibility = View.VISIBLE
        content_container.visibility = View.GONE
    }

    private fun hideEmptyState(){
        content_container.visibility = View.VISIBLE
        empty_genre.visibility = View.GONE
    }

}