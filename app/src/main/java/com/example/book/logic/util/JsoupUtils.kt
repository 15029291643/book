package com.example.book.logic.util

import android.text.TextUtils.split
import android.util.Log
import com.example.book.logic.model.Book
import com.example.book.logic.model.Chapter
import org.jsoup.Jsoup
import kotlin.text.split

object JsoupUtils {
    private const val TAG = "JsoupUtils"
    private fun getParse(html: String) = Jsoup.parse(html)

    fun getBookList(html: String): List<Book> {
        val bookList = ArrayList<Book>()
        run loop@{
            getParse(html).select("body > div.wrap > div.search-html-box.clearfix > div.search-main.fl > div.search-tab > div")
                .forEach {
                    if (it.className() == "h20-blank") {
                        return@loop
                    }
                    val img = it.selectFirst("div.imgbox.fl.se-result-book > a > img").attr("src")
                    val href = it.selectFirst("div.imgbox.fl.se-result-book > a").attr("href")
                    val name = it.selectFirst("div.fl.se-result-infos > h2 > a").text()
                    val intro = it.selectFirst("div.fl.se-result-infos > p").text()
                    val key = it.selectFirst("div.fl.se-result-infos > div.key-word").text()
                    val info = it.selectFirst("div.fl.se-result-infos > div.bookinfo").text()
                    val infoSplit = info.split(" |")
                    bookList.add(
                        Book(
                            img = img,
                            href = href,
                            name = name,
                            intro = intro,
                            author = infoSplit[0],
                            labels = listOf(infoSplit[1], infoSplit[2]),
                            number = infoSplit[3],
                            keys = key.replace("关键词：", " ").trim().split(" ")
                        ).also {
                            Log.e(TAG, "getBookList: $name")
                        }
                    )
                }
        }
        return bookList
    }

    fun getBook(html: String): Book {
        getParse(html).apply {
            val img =
                selectFirst("body > div.wrap > div.book-html-box.clearfix > div.book-top.clearfix > div.book-main.fl > div.book-detail.clearfix > div.book-img.fl > img").attr(
                    "src"
                )
            selectFirst("body > div.wrap > div.book-html-box.clearfix > div.book-top.clearfix > div.book-main.fl > div.book-detail.clearfix > div.book-info").apply {
                val name = selectFirst("div.book-name").text()
                val label = selectFirst("div.book-label").text()
                val number = selectFirst("div.nums").text()
                val intro = selectFirst("div.book-dec.Jbook-dec.hide").text()
                val href = selectFirst("div.btn-group > a").attr("href")
                val labels = label.split(" ".toRegex())
                return Book(
                    img = img,
                    name = name,
                    labels = labels.subList(0, 2),
                    keys = labels.subList(2, labels.size),
                    intro = intro,
                    number = number.split(" ".toRegex())[1],
                    href = href,
                ).apply {
                    println(this)
                }
            }
        }
    }

    fun getChapter(html: String): Chapter {
        getParse(html).apply {
            val title =
                selectFirst("#readerFt > div > div.reader_box > div.title > div.title_txtbox").text()
            val info = selectFirst("#readerFt > div > div.reader_box > div.bookinfo").text()
            val content = selectFirst("#readerFt > div > div.reader_box > div.content").text()
            val infoSplit = info.replace(" ", "").replace("全文阅读", "").split("|")
            return Chapter(
                title = title,
                author = infoSplit[0].split("：")[1],
                number = infoSplit[1].split("：")[1],
                updateTime = infoSplit[2].split("：")[1],
                contents = content.split(" ")
            )
        }
    }
}