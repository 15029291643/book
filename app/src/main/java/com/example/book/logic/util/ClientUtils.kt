package com.example.book.logic.util

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import okhttp3.OkHttpClient

object ClientUtils {
    val instance = OkHttpClient()
}