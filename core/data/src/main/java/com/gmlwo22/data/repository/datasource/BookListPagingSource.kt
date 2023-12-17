package com.gmlwo22.data.repository.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gmlwo22.data.model.toDomain
import com.gmlwo22.data.network.ITBookStoreApiService
import com.gmlwo22.domain.model.BookItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class BookListPagingSource(
    private val searchQuery: String,
    private val itBookStoreApiService: ITBookStoreApiService
) : PagingSource<Int, BookItem>() {

    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {
        return try {
            withContext(Dispatchers.IO) {
                val page = params.key ?: 1
                val response = itBookStoreApiService.searchBook(query = searchQuery, page = page.toString())
                val bookList = response.body()?.toDomain() ?: mutableListOf()
                val prevPage = if (page == 1) null else page - 1
                val nextPage = if (bookList.isEmpty()) null else page + 1
                return@withContext LoadResult.Page(
                    data = bookList,
                    prevKey = prevPage,
                    nextKey = nextPage
                )
            }
        }
        catch (exception: IOException) {
            Log.println(Log.ERROR, null, "exception : $exception")
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
        catch (exception: HttpException) {
            Log.println(Log.ERROR, null, "exception : $exception")
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }
}