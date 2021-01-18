package com.example.skywebproandroidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.skywebproandroidapp.R
import com.example.skywebproandroidapp.model.PhotoItemItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.soccer_item.view.*

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<PhotoItemItem>() {
        override fun areItemsTheSame(oldItem: PhotoItemItem, newItem: PhotoItemItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoItemItem, newItem: PhotoItemItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.soccer_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((PhotoItemItem) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Picasso.get().load(article.download_url).into(ivArticleImage)
            author.text = article.author

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (PhotoItemItem) -> Unit) {
        onItemClickListener = listener
    }

}