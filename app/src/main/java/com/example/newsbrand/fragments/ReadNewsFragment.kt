package com.example.newsbrand.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.example.newsbrand.databinding.FragmentReadNewsBinding
import com.example.newsbrand.response.news_module.Article
import com.example.newsbrand.viewmodel.ReadNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReadNewsFragment : Fragment() {

    @Inject
    lateinit var readNewsViewModel: ReadNewsViewModel

    private lateinit var bindingFragmentReadNewsBinding: FragmentReadNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentReadNewsBinding =
            FragmentReadNewsBinding.inflate(layoutInflater, container, false)
        readNewsViewModel = ViewModelProvider(this)[ReadNewsViewModel::class.java]

        return bindingFragmentReadNewsBinding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //this is for checking this fragment being called by which newsListFragment or SavedNewsFragment
        val checkIncomingFragment = arguments?.getBoolean("ARTICLE_FROM_NEWS_FRAGMENT", false)

        if (checkIncomingFragment == true) {
            //fragment started from NewsListFragment
            val fromNewsFragment = arguments?.getSerializable("ARTICLES") as? Article
            readNewsViewModel.setReadData(fromNewsFragment!!)

        } else {
            //fragment started from SavedNewsFragment
            val fromSavedFragment =
                arguments?.getSerializable("ARTICLES") as? Article
            readNewsViewModel.setReadData(fromSavedFragment!!)

        }

        //observing the data from readNews viewModel class
        readNewsViewModel.publicReadLiveData.observe(requireActivity()) {
            bindingFragmentReadNewsBinding.fullContentRF.text = it.content
            bindingFragmentReadNewsBinding.newsTitleRF.text = it.title
            bindingFragmentReadNewsBinding.newDateRF.text = it.publishedAt?.substring(0, 10)
            bindingFragmentReadNewsBinding.newsAuthorRF.text = it.author
            Glide.with(this).load(it.urlToImage).into(bindingFragmentReadNewsBinding.imageView)
        }

        bindingFragmentReadNewsBinding.backButtonRF.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun /**/setUpBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    Toast.makeText(requireContext(), "go back....", Toast.LENGTH_SHORT).show()
                    isEnabled = true
                }
            }
        })
    }


}
