package com.averoes.booksapp.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.averoes.booksapp.ui.book.BookDetail
import com.averoes.booksapp.R
import com.averoes.booksapp.model.book.ResponseBook
import com.averoes.booksapp.model.book.Result
import com.averoes.booksapp.utils.Constant.API_KEY
import com.averoes.booksapp.utils.Constant.IMAGE_BASEURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_books.view.*

class BooksAdapter (var books:ResponseBook) : RecyclerView.Adapter<BooksAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false))
    }

    override fun getItemCount() = books.result.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(books.result[position])
    }

    class Holder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(item:Result){

            itemView.title_book_item.text = item.title
            itemView.author_books_item.text = item.writerByWriterId.userByUserId.name

            Picasso.get().load(IMAGE_BASEURL + item.coverUrl + API_KEY)
                .resize(512, 512)
                .placeholder(R.drawable.ic_baseline_category)
                .into(itemView.image_book_item)

            Log.d("IMAGE", IMAGE_BASEURL + item.coverUrl + API_KEY)

            itemView.setOnClickListener {
                val booksDetail = Intent(itemView.context, BookDetail::class.java)
                booksDetail.putExtra("book_id", item.id)
                itemView.context.startActivity(booksDetail)
            }
        }
    }
}