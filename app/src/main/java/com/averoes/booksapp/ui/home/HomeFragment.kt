package com.averoes.booksapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.booksapp.R
import com.averoes.booksapp.model.book.ResponseBook
import com.averoes.booksapp.model.genre.Genre
import com.averoes.booksapp.utils.ConfigRetrofit
import com.github.ybq.android.spinkit.style.FoldingCube
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val animation = FoldingCube();
        loading.setIndeterminateDrawable(animation);
        getGender()
        getLatestBooks()
    }

    private fun getLatestBooks() {
        enableLoading()
        hideEmptyState()
        ConfigRetrofit().getService().getLatestBook(10).enqueue(object : Callback<ResponseBook> {
            override fun onFailure(call: Call<ResponseBook>, t: Throwable) {
                disabledLoading()
                showEmptyState()
                Log.d("RESPONSE", "Error ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<ResponseBook>, response: Response<ResponseBook>) {
                Log.d("Response", response.body().toString())
                latest_book_list.adapter = response.body()?.let { BooksAdapter(it) }
                latest_book_list.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                disabledLoading()
                hideEmptyState()
            }

        })
    }

    private fun getGender() {
        enableLoading()
        hideEmptyState()
        ConfigRetrofit().getService().getGenre().enqueue(object : Callback<Genre?> {
            override fun onFailure(call: Call<Genre?>, t: Throwable) {
                disabledLoading()
                showEmptyState()
                Log.d("RESPONSE", "Error ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Genre?>, response: Response<Genre?>) {
                if (response.isSuccessful) {
                    genre_list.adapter = GenreAdapter(response.body()?.resource)
                    genre_list.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    disabledLoading()
                    hideEmptyState()
                }
            }

        })
    }

    private fun enableLoading() {
        loading.visibility = View.VISIBLE
        home_container.visibility = View.INVISIBLE
    }

    private fun disabledLoading() {
        loading.visibility = View.GONE
        home_container.visibility = View.VISIBLE
    }
    private fun showEmptyState(){
        home_container.visibility = View.GONE
        empty_state.visibility = View.VISIBLE
    }

    private fun hideEmptyState(){
        home_container.visibility = View.VISIBLE
        empty_state.visibility = View.GONE
    }
}