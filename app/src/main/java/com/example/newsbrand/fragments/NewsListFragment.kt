package com.example.newsbrand.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbrand.R
import com.example.newsbrand.adapters.NewsListAdapter
import com.example.newsbrand.api.NewsOnclick
import com.example.newsbrand.databinding.FragmentNewsListBinding
import com.example.newsbrand.viewmodel.MainViewModel
import com.example.newsbrand.viewmodel.ReadNewsViewModel
import com.example.newsbrand.viewmodel.SavedFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment(), NewsOnclick {
    lateinit var binding: FragmentNewsListBinding
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var savedFragmentViewModel: SavedFragmentViewModel

    lateinit var readNewsViewModel: ReadNewsViewModel

    lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        readNewsViewModel = ViewModelProvider(this)[ReadNewsViewModel::class.java]
        binding.recyclerViewNLF.layoutManager = LinearLayoutManager(requireContext())
        mainViewModel.publicLivedata.observe(requireActivity()){
            adapter = NewsListAdapter(it.articles,this,savedFragmentViewModel)
            binding.recyclerViewNLF.adapter = adapter

        }
        binding.saveButtonNLF.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_newsListFragment_to_savedNewsFragment)
        }


        binding.searchNewsNLF.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun newClick(position: Int) {

        val bundle = Bundle()
        bundle.putInt("position",position)
        Navigation.findNavController(binding.root).navigate(R.id.action_newsListFragment_to_readNewsFragment,bundle)
    }
}