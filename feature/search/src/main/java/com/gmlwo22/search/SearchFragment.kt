package com.gmlwo22.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmlwo22.common.navigation.NavigationUtil
import com.gmlwo22.domain.model.BookItem
import com.gmlwo22.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _biding: FragmentSearchBinding? = null
    private val binding get() = _biding!!
    private val viewModel: SearchViewModel by viewModels()
    private val bookPagingAdapter: BookPagingAdapter by lazy {
        BookPagingAdapter(object : onItemClickListener {
            override fun onItemClick(item: BookItem) {
                item.isbn13?.let {
                    (activity as NavigationUtil).navigateToDetail(it)
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _biding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservable()
    }

    private fun setupUI() {
        binding.listView.apply {
            setHasFixedSize(true)
            adapter = bookPagingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        binding.searchBar.requestFocus()
        binding.searchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.searchBar.text.toString().isNotBlank()) {
                    binding.searchBar.text?.let {
                        viewModel.searchBook(it.toString())
                    }
                }
                binding.searchBar.clearFocus()
                val inputMethodManager = binding.searchBar.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.searchBar.windowToken, 0)
            }
            true
        }
    }

    private fun setupObservable() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagingDataFlow.collect {
                bookPagingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _biding = null
    }

    companion object {
        const val TAG = "SearchFragment"
    }

}