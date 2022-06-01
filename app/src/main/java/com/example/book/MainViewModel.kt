package com.example.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book.logic.model.Book
import com.example.book.logic.util.OkhttpUtils
import com.example.book.logic.util.RxJavaUtils
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books

    fun search(name: String) {
        Thread{
            _books.postValue(OkhttpUtils.getBookList(name))
        }.start()
    }
}