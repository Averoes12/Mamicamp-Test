package com.averoes.booksapp.ui.home

import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.averoes.booksapp.GenreActivty
import com.averoes.booksapp.R
import com.averoes.booksapp.model.genre.Genre
import com.averoes.booksapp.model.genre.ResourceItem
import com.averoes.booksapp.utils.Constant.GENRE_ID
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter(var genre: List<ResourceItem?>?) : RecyclerView.Adapter<GenreAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false))
    }

    override fun getItemCount() = genre!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(genre, position)
    }
    class Holder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(item:List<ResourceItem?>?, position: Int){
            itemView.genre_button.text = item!![position]?.title

            itemView.genre_button.setOnClickListener {
                val genreBooks = Intent(itemView.context, GenreActivty::class.java)
                genreBooks.putExtra(GENRE_ID, item[position]?.id)
                genreBooks.putExtra("name_genre", item[position]?.title)
                itemView.context.startActivity(genreBooks)
            }
        }
    }
}