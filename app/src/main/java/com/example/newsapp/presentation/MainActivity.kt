package com.example.newsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.adapter.NewsAdapter
import com.example.newsapp.presentation.viewModel.NewsViewModel
import com.example.newsapp.presentation.viewModel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(navController)

        newsViewModel = ViewModelProvider(this, newsViewModelFactory)[NewsViewModel::class.java]
    }
}