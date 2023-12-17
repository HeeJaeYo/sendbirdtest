package com.gmlwo22.domain.usecase

import androidx.paging.PagingData
import com.gmlwo22.domain.model.BookItem
import com.gmlwo22.domain.repository.ITBookStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val itBookStoreRepository: ITBookStoreRepository
) {
    suspend operator fun invoke(query: String) : Flow<PagingData<BookItem>> {
        return itBookStoreRepository.searchBooks(query)
    }
}