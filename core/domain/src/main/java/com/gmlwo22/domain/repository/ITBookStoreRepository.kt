package com.gmlwo22.domain.repository

import androidx.paging.PagingData
import com.gmlwo22.domain.model.BookDetailInfo
import com.gmlwo22.domain.model.BookItem
import kotlinx.coroutines.flow.Flow

interface ITBookStoreRepository {
    suspend fun searchBooks(query: String): Flow<PagingData<BookItem>>
    suspend fun getBookDetail(isbn13: String): BookDetailInfo?
}