package com.gmlwo22.detailbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmlwo22.domain.model.BookDetailInfo
import com.gmlwo22.domain.usecase.GetBookDetailInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetailInfoUseCase: GetBookDetailInfoUseCase
) : ViewModel() {
    private val mutableBookDetailUiStateFlow = MutableStateFlow<BookDetailUiState>(BookDetailUiState.Loading)
    val bookDetailUiStateFlow = mutableBookDetailUiStateFlow.asStateFlow()
    fun getBookDetail(isbn13: String) {
        viewModelScope.launch {
            try {
                val result = getBookDetailInfoUseCase(isbn13 = isbn13)
                if (result == null) {
                    mutableBookDetailUiStateFlow.tryEmit(BookDetailUiState.Error)
                }
                else {
                    if (result.error == "0") {
                        mutableBookDetailUiStateFlow.tryEmit(BookDetailUiState.BookDetail(result))
                    }
                    else {
                        mutableBookDetailUiStateFlow.tryEmit(BookDetailUiState.Error)
                    }
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                mutableBookDetailUiStateFlow.tryEmit(BookDetailUiState.Error)
            }
        }
    }
}

sealed interface BookDetailUiState {
    data class BookDetail(val info: BookDetailInfo) : BookDetailUiState
    object Error : BookDetailUiState
    object Loading : BookDetailUiState
}