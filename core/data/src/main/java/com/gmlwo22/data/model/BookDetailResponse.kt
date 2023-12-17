package com.gmlwo22.data.model

import com.gmlwo22.domain.model.BookDetailInfo
import java.io.Serializable

/**
 * {
"error": "0"
"title": "Securing DevOps"
"subtitle": "Security in the Cloud"
"authors": "Julien Vehent"
"publisher": "Manning"
"isbn10": "1617294136"
"isbn13": "9781617294136"
"pages": "384"
"year": "2018"
"rating": "5"
"desc": "An application running in the cloud can benefit from incredible efficiencies, but they come with unique security threats too. A DevOps team's highest priority is understanding those risks and hardening the system against them.Securing DevOps teaches you the essential techniques to secure your cloud ..."
"price": "$26.98"
"image": "https://itbook.store/img/books/9781617294136.png"
"url": "https://itbook.store/books/9781617294136"
"pdf": {
"Chapter 2": "https://itbook.store/files/9781617294136/chapter2.pdf",
"Chapter 5": "https://itbook.store/files/9781617294136/chapter5.pdf"
}
}
 */
data class BookDetailResponse(
    val error: String?,
    val title: String?,
    val subtitle: String?,
    val authors: String?,
    val publisher: String?,
    val isbn10: String?,
    val isbn13: String?,
    val pages: String?,
    val year: String?,
    val rating: String?,
    val desc: String?,
    val price: String?,
    val image: String?,
    val url : String?,
    val pdf: Map<String, String>?
): Serializable

fun BookDetailResponse.toDomain(): BookDetailInfo {
    return BookDetailInfo(error, title, subtitle, authors, publisher, isbn10, isbn13, pages, year, rating, desc, price, image, url, pdf)
}