package com.gmlwo22.domain.model

import java.io.Serializable

data class BookItem(
    val title: String?,
    val subtitle: String?,
    val isbn13: String?,
    val price: String?,
    val image: String?,
    val url: String?
): Serializable
