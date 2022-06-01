package com.example.book.logic.util

import com.example.book.logic.model.Book
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.core.FlowableOnSubscribe

object RxJavaUtils {
    fun getBookList(name: String) = Flowable.create(FlowableOnSubscribe<List<Book>> { emitter ->
        emitter.onNext(
            OkhttpUtils.getBookList(name)
        )
    }, BackpressureStrategy.ERROR)
}