package com.gmlwo22.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmlwo22.domain.model.BookItem
import com.gmlwo22.search.databinding.ViewItemBookBinding

class BookPagingAdapter(private val listener: onItemClickListener) : PagingDataAdapter<BookItem, BookItemViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        getItem(position)?.let {bookItem ->
            Log.println(Log.DEBUG, null, "#### $bookItem")
            holder.bind(bookItem)
            holder.itemView.setOnClickListener {
                listener.onItemClick(bookItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ViewItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookItem>() {
            override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

interface onItemClickListener {
    fun onItemClick(item: BookItem)
}

class BookItemViewHolder(private val binding: ViewItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BookItem) {
        binding.book = item
        binding.title.text = item.title
        Glide.with(binding.root)
            .load(item.image)
            .into(binding.bookImage)
    }
}