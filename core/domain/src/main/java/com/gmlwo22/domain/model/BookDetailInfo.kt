package com.gmlwo22.domain.model

import java.io.Serializable

data class BookDetailInfo(
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
