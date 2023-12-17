package com.gmlwo22.detailbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.gmlwo22.detailbook.databinding.FragmentBookDetailBinding
import com.gmlwo22.domain.model.BookDetailInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailFragment : Fragment() {
    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookDetailViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupObservable()
        val id = arguments?.getString("id", null)
        if (id.isNullOrBlank()) {
            findNavController().popBackStack()
        }
        else {
            viewModel.getBookDetail(id)
        }
    }

    private fun setupObservable() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookDetailUiStateFlow.collect {
                when (it) {
                    is BookDetailUiState.Loading -> {
                        binding.loading.isVisible = true
                    }
                    is BookDetailUiState.Error -> {
                        binding.loading.isVisible = false
                        binding.errorText.isVisible = true
                    }
                    is BookDetailUiState.BookDetail -> {
                        binding.loading.isVisible = false
                        bindBookDetailInfo(it.info)
                    }
                }
            }
        }
    }

    private fun bindBookDetailInfo(info: BookDetailInfo) {
        binding.bookDetail = info
        Glide.with(binding.bookDetailImage)
            .load(info.image)
            .into(binding.bookDetailImage)
        binding.bookDetailContainer.isVisible = true
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}