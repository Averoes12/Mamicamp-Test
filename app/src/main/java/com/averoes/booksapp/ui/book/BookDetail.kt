package com.averoes.booksapp.ui.book

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.booksapp.R
import com.averoes.booksapp.model.ResponseBookDetail
import com.averoes.booksapp.model.ResultBook
import com.averoes.booksapp.ui.home.GenreAdapter
import com.averoes.booksapp.utils.ConfigRetrofit
import com.averoes.booksapp.utils.Constant.API_KEY
import com.averoes.booksapp.utils.Constant.IMAGE_BASEURL
import com.github.ybq.android.spinkit.style.FoldingCube
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetail : AppCompatActivity() {

    lateinit var bookDetail: ResultBook
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        supportActionBar!!.hide()
        val animation = FoldingCube();
        loading_detail.setIndeterminateDrawable(animation);
        Log.d("CEK DETAIL", intent.getIntExtra("book_id",0).toString())
        getBookDetail()

        icon_back.setOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getBookDetail() {
        enableLoading()
        hideEmptyState()
        ConfigRetrofit().getService().getDetailBook(intent.getIntExtra("book_id", 0).toString())
            .enqueue(object : Callback<ResponseBookDetail> {
                override fun onFailure(call: Call<ResponseBookDetail>, t: Throwable) {
                    Log.d("RESPONSE", "Error ${t.localizedMessage}")
                    disabledLoading()
                    showEmptyState()
                }

                override fun onResponse(
                    call: Call<ResponseBookDetail>,
                    response: Response<ResponseBookDetail>
                ) {
                    Log.d("Response", response.body().toString())
                    hideEmptyState()
                    disabledLoading()
                    if (response.body()?.success == true) {
                        bookDetail = response.body()?.result!!
                        title_detail.text = bookDetail.title
                        author_detail.text = "by ${bookDetail.writerByWriterId?.userByUserId?.name}"
                        status_detail.text = bookDetail.status
                        title_toolbar.text = bookDetail.title

                        Picasso.get().load(IMAGE_BASEURL + bookDetail.coverUrl + API_KEY).placeholder(R.drawable.ic_broken)
                            .into(poster_book_detail)
                        Picasso.get().load(IMAGE_BASEURL + bookDetail.coverUrl + API_KEY).placeholder(R.drawable.ic_broken).resize(240,240)
                            .into(bg_book_detail)

                        genre_list_detail.adapter = GenreAdapter(bookDetail.genres)
                        genre_list_detail.layoutManager = LinearLayoutManager(this@BookDetail, LinearLayoutManager.HORIZONTAL, false)

                        rating_detail.rating = bookDetail.decimalRate!!.toFloat()
                        val mimeType = "text/html"
                        val encoding = "UTD-8"
                        synopsis_detail.loadDataWithBaseURL("", bookDetail.synopsis,mimeType, encoding,"")

                        review_list.adapter = ReviewAdapter(bookDetail.reviews?.filter { it.userByReviewerId?.username?.length!! <= 10  })
                        review_list.layoutManager = LinearLayoutManager(this@BookDetail, LinearLayoutManager.HORIZONTAL, false)

                    }else{
                        showEmptyState()
                    }
                }

            })
    }

    private fun enableLoading() {
        loading_detail.visibility = View.VISIBLE
        book_detail_container.visibility = View.GONE
        content_detail_container.visibility = View.GONE
    }

    private fun disabledLoading() {
        loading_detail.visibility = View.GONE
        book_detail_container.visibility = View.VISIBLE
        content_detail_container.visibility = View.VISIBLE
    }

    private fun showEmptyState() {
        empty_detail.visibility = View.VISIBLE
        book_detail_container.visibility = View.GONE
    }

    private fun hideEmptyState() {
        book_detail_container.visibility = View.VISIBLE
        empty_detail.visibility = View.GONE
    }
}