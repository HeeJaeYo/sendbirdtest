package com.gmlwo22.data.model

import com.gmlwo22.domain.model.BookItem
import java.io.Serializable

data class SearchResponse(
    val total : Int,
    val page : Int,
    val books: List<Book>
): Serializable

data class Book(
    val title: String?,
    val subtitle: String?,
    val isbn13: String?,
    val price: String?,
    val image: String?,
    val url: String?
): Serializable

fun SearchResponse.toDomain(): MutableList<BookItem> {
    return books.map { BookItem(it.title, it.subtitle, it.isbn13, it.price, it.image, it.url) }.toMutableList()
}