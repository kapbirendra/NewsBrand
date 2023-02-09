package com.example.newsbrand.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.newsbrand.R
import com.example.newsbrand.databinding.FragmentNewsListBinding
import com.example.newsbrand.databinding.FragmentReadNewsBinding
import com.example.newsbrand.viewmodel.ReadNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReadNewsFragment : Fragment() {


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
        readNewsViewModel.publicReadLiveData.observe(viewLifecycleOwner){
            println("### it is $it")
        }

    }
}