package com.gmlwo22.sendbirdtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gmlwo22.common.navigation.NavigationUtil
import com.gmlwo22.sendbirdtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationUtil {
    private lateinit var binding: ActivityMainBinding
    private val navController : NavController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

    }
    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun navigateToDetail(isbn13: String) {
        navController.navigate(R.id.action_to_book_detail, bundleOf("id" to isbn13))
    }
}