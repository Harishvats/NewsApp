package com.example.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.util.Resource
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.presentation.adapter.NewsAdapter
import com.example.newsapp.presentation.viewModel.NewsViewModel


class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    // called immediately after all views are created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        initRecyclerView()
        viewNewsList()
    }


    private fun initRecyclerView() {
        binding.rvNews.apply {

            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressbar.visibility = View.GONE
    }

    private fun viewNewsList() {
        newsViewModel.getNewsHeadlines(country, page)
        newsViewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        pages = if (it.totalResults % 20 == 0) {
                            it.totalResults / 20
                        } else {
                            it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages

                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                newsViewModel.getNewsHeadlines(country, page)
                isScrolling = false

            }
        }
    }
}