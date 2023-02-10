package com.example.newsbrand.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.example.newsbrand.R
import com.example.newsbrand.databinding.FragmentNewsListBinding
import com.example.newsbrand.databinding.FragmentReadNewsBinding
import com.example.newsbrand.viewmodel.MainViewModel
import com.example.newsbrand.viewmodel.ReadNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReadNewsFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    lateinit var readNewsViewModel: ReadNewsViewModel

    lateinit var bindingFragmentReadNewsBinding: FragmentReadNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentReadNewsBinding = FragmentReadNewsBinding.inflate(layoutInflater, container, false)
        readNewsViewModel = ViewModelProvider(this)[ReadNewsViewModel::class.java]

        return bindingFragmentReadNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val position = arguments?.getInt("position")

        mainViewModel.publicLivedata.observe(viewLifecycleOwner){
            val news = it.articles[position!!]

            bindingFragmentReadNewsBinding.fullContentRF.text = news.content
            bindingFragmentReadNewsBinding.newsTitleRF.text = news.title
            bindingFragmentReadNewsBinding.newDateRF.text = news.publishedAt?.substring(0,10)
            bindingFragmentReadNewsBinding.newsAuthorRF.text = news.author
            Glide.with(this).load(it.articles[position].urlToImage).into(bindingFragmentReadNewsBinding.imageView)
        }

        setUpBackPressed()

        bindingFragmentReadNewsBinding.backButtonRF.setOnClickListener {
            requireActivity().onBackPressed()
//            findNavController().navigate(R.id.action_readNewsFragment_to_newsListFragment)
        }

    }

    private fun setUpBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object :OnBackPressedCallback(false){
            override fun handleOnBackPressed() {
                if (isEnabled){
                    Toast.makeText(requireContext(),    "go back....", Toast.LENGTH_SHORT).show()
                    isEnabled = true
                }
            }
        })
    }


}
