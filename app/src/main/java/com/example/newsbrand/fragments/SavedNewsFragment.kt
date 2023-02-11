package com.example.newsbrand.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsbrand.adapters.SavedNewsAdapter
import com.example.newsbrand.databinding.FragmentSavedNewsBinding
import com.example.newsbrand.viewmodel.SavedFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    lateinit var adapter: SavedNewsAdapter

    lateinit var binding:FragmentSavedNewsBinding
    @Inject
    lateinit var savedNewsFragmentViewModel: SavedFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedNewsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerViewSF.layoutManager = LinearLayoutManager(requireContext())
        savedNewsFragmentViewModel.readSavedArticleFromVm().observe(requireActivity()){
            adapter = SavedNewsAdapter(it,requireContext(),savedNewsFragmentViewModel )
            binding.recyclerViewSF.adapter = adapter
        }

        binding.backButtonSF.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.searchNewsSF.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

    }

    private fun setUpBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(false){
            override fun handleOnBackPressed() {
                if (isEnabled){
                    Toast.makeText(requireContext(),    "go back....", Toast.LENGTH_SHORT).show()
                    isEnabled = true
                }
            }
        })
    }

}