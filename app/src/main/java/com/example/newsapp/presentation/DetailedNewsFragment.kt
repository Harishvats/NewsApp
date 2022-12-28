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

class DetailedNewsFragment : Fragment() {

    private lateinit var binding: FragmentDetailedNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailedNewsBinding.bind(view)
        val args by navArgs<DetailedNewsFragmentArgs>()
        val article = args.selectedArticle

        binding.infoWebView.apply {
            webViewClient = WebViewClient()
            if (!article.url.isNullOrBlank())
                loadUrl(article.url)
        }

    }

}