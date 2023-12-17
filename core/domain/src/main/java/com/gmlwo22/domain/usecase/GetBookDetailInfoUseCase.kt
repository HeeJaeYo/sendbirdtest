package com.gmlwo22.domain.usecase

import com.gmlwo22.domain.model.BookDetailInfo
import com.gmlwo22.domain.repository.ITBookStoreRepository
import javax.inject.Inject

class GetBookDetailInfoUseCase @Inject constructor(
    private val itBookStoreRepository: ITBookStoreRepository
) {
    suspend operator fun invoke(isbn13: String): BookDetailInfo? {
        return itBookStoreRepository.getBookDetail(isbn13)
    }
}