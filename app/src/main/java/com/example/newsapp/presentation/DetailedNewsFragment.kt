package com.example.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailedNewsBinding
import com.example.newsapp.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class DetailedNewsFragment : Fragment() {

    private lateinit var binding: FragmentDetailedNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailedNewsBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel
        val args by navArgs<DetailedNewsFragmentArgs>()
        val article = args.selectedArticle
        val hideSaveBtn = args.hideSaveBtn

        if (hideSaveBtn) {
            binding.fabSave.visibility = View.GONE
        } else {
            binding.fabSave.visibility = View.VISIBLE
        }

        binding.infoWebView.apply {
            webViewClient = WebViewClient()
            if (!article.url.isNullOrBlank()) loadUrl(article.url)
        }
        binding.fabSave.setOnClickListener {
            newsViewModel.saveArticle(article)
            Snackbar.make(view, "Saved Successfully!!", Snackbar.LENGTH_SHORT).show()
        }

    }

}