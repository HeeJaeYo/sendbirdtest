package com.gmlwo22.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gmlwo22.domain.model.BookItem
import com.gmlwo22.domain.usecase.SearchBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBookUseCase: SearchBookUseCase
) : ViewModel() {
    companion object {
        const val TAG = "SearchViewModel"
    }

    private val searchQueryFlow: MutableStateFlow<String> = MutableStateFlow("")
    val pagingDataFlow: Flow<PagingData<BookItem>> = searchQueryFlow
        .flatMapLatest { searchBookUseCase(it) }
        .cachedIn(viewModelScope)

    fun searchBook(query: String) {
        Log.d(TAG, "searchBook() $query")
        searchQueryFlow.tryEmit(query)
    }
}