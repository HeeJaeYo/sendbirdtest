package com.gmlwo22.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gmlwo22.data.model.toDomain
import com.gmlwo22.data.network.ITBookStoreApiService
import com.gmlwo22.data.repository.datasource.BookListPagingSource
import com.gmlwo22.domain.model.BookDetailInfo
import com.gmlwo22.domain.model.BookItem
import com.gmlwo22.domain.repository.ITBookStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ITBookStoreRepositoryImpl @Inject constructor(
    private val apiService: ITBookStoreApiService,
) : ITBookStoreRepository {
    override suspend fun getBookDetail(id: String): BookDetailInfo? {
        return withContext(Dispatchers.IO) {
            apiService.detailBooks(id).body()?.toDomain()
        }
    }

    override suspend fun searchBooks(query: String): Flow<PagingData<BookItem>> {
        Log.println(Log.DEBUG, null, "searchBooks() query : $query")
        return Pager(PagingConfig(pageSize = 10, enablePlaceholders = false)) {
            BookListPagingSource(searchQuery = query, itBookStoreApiService = apiService)
        }.flow
    }
}