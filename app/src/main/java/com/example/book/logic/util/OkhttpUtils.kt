package com.example.book.logic.util

import android.provider.SyncStateContract
import android.util.Log
import com.example.book.logic.util.OkhttpUtils.getHtml
import com.example.book.logic.util.OkhttpUtils.getRequest
import okhttp3.Request
import java.lang.Exception

object OkhttpUtils {
    private const val TAG = "OkhttpUtils"

    private fun getRequest(url: String) = Request.Builder().url(url).build()

    private fun getHtml(url: String): String {
        Log.e(TAG, "getHtml: $url")
        return ClientUtils.instance.newCall(getRequest(url)).execute().body()!!.string()
    }

    fun getBookList(name: String) = JsoupUtils.getBookList(getHtml(ConstantUtils.SEARCH_URL + name))

}

