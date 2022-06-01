package com.example.book.logic.model

data class Chapter(
    var url: String = "",
    var title: String = "",
    var number: String = "",
    var updateTime: String = "",
    var author: String = "",
    var contents: List<String> = ArrayList()
)
