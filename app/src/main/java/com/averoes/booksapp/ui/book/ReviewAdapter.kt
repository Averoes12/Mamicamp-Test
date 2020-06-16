package com.averoes.booksapp.ui.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.averoes.booksapp.R
import com.averoes.booksapp.model.ReviewsItem
import com.averoes.booksapp.utils.Constant.API_KEY
import com.averoes.booksapp.utils.Constant.IMAGE_BASEURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_reviews.view.*
import java.text.SimpleDateFormat

class ReviewAdapter(var review:List<ReviewsItem>?) : RecyclerView.Adapter<ReviewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_reviews, parent, false))
    }

    override fun getItemCount() = review!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        this.review?.get(position)?.let { holder.bind(it) }
    }

    class Holder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(item:ReviewsItem){

            Picasso.get().load(IMAGE_BASEURL + item.userByReviewerId?.photoUrl + API_KEY).placeholder(R.drawable.ic_person)
                .into(itemView.photo_review)
            itemView.username_user.text = item.userByReviewerId?.username
            itemView.email_user.text = item.userByReviewerId?.email

            val parser =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val formatter =
                SimpleDateFormat("dd MM yyyy")
            val output = formatter.format(parser.parse(item.updatedAt))
            itemView.update_at.text = output
            itemView.body_review.text = item.review
            if (item.rating != null)itemView.review_rating.rating = item.rating.toFloat() else itemView.review_rating.rating = 0f

        }
    }
}