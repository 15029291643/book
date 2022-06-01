package com.example.book.logic.model

data class Book(
    var url: String = "",
    var href: String = "",
    var img: String = "",
    var name: String = "",
    var author: String = "",
    var labels: List<String> = ArrayList(),
    var keys: List<String> = ArrayList(),
    var number: String = "",
    var intro: String = "",
)