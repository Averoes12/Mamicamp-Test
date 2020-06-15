package com.averoes.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.averoes.booksapp.utils.Constant.API_KEY
import com.averoes.booksapp.utils.Constant.IMAGE_BASEURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        title_detail.text = intent.getStringExtra("title")
        author_detail.text = intent.getStringExtra("author")
        status_detail.text = intent.getStringExtra("status")

        Picasso.get().load(IMAGE_BASEURL + intent.getStringExtra("cover") + API_KEY).into(poster_book_detail)
        Picasso.get().load(IMAGE_BASEURL + intent.getStringExtra("cover") + API_KEY).into(bg_book_detail)
    }
}