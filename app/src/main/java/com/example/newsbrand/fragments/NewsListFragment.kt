package com.example.newsbrand.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbrand.adapters.NewsListAdapter
import com.example.newsbrand.databinding.FragmentNewsListBinding
import com.example.newsbrand.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    lateinit var binding: FragmentNewsListBinding
    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerViewNLF.layoutManager = LinearLayoutManager(requireContext())
        mainViewModel.publicLivedata.observe(requireActivity()){
            binding.recyclerViewNLF.adapter = NewsListAdapter(it.articles)
            it.articles.forEach {
                Log.d("shwotheNews",it.title!!)
            }
        }
    }
}