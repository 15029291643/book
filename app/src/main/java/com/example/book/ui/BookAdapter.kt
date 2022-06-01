package com.example.book.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book.databinding.ViewBookBinding
import com.example.book.logic.model.Book
import java.util.function.BinaryOperator

class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var books: List<Book>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setBooks(books: List<Book>?) {
        this.books = books
        notifyDataSetChanged()
    }

    class BookViewHolder(val binding: ViewBookBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ViewBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.binding.let {
            books!![position].apply {
                it.bookName.text = name
                it.bookAuthor.text = author
                it.bookIntro.text = intro
                it.bookLabels.text = labels[0]
                // Glide.with(context).load(img).into(it.bookImg);
            }
        }
    }

    override fun getItemCount() = books?.size ?: 0
}