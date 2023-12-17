package com.gmlwo22.data.network

import com.gmlwo22.data.BuildConfig
import com.gmlwo22.data.model.BookDetailResponse
import com.gmlwo22.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITBookStoreApiService {
    companion object {
        const val BASE_URL = BuildConfig.API_URL
    }

    @GET("/1.0/search/{query}/{page}")
    suspend fun searchBook(
        @Path("query") query: String,
        @Path("page") page: String
    ): Response<SearchResponse>

    @GET("/1.0/search/{query}")
    suspend fun searchBook(
        @Path("query") query: String
    ): Response<SearchResponse>

    @GET("/1.0/books/{isbn13}")
    suspend fun detailBooks(
        @Path("isbn13") isbn13: String
    ): Response<BookDetailResponse>
}