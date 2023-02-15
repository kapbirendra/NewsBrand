package com.example.newsbrand.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SharedMemory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbrand.R
import com.example.newsbrand.adapters.NewsListAdapter
import com.example.newsbrand.api.NewsOnclick
import com.example.newsbrand.databinding.FragmentNewsListBinding
import com.example.newsbrand.response.news_module.Article
import com.example.newsbrand.ui.LoginActivity
import com.example.newsbrand.utils.Session
import com.example.newsbrand.viewmodel.MainViewModel
import com.example.newsbrand.viewmodel.ReadNewsViewModel
import com.example.newsbrand.viewmodel.SavedFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment(), NewsOnclick {
    private lateinit var binding: FragmentNewsListBinding
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var savedFragmentViewModel: SavedFragmentViewModel
@Inject
lateinit var session:Session
    private lateinit var readNewsViewModel: ReadNewsViewModel

    lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        val saveList = mutableListOf<String>()
        savedFragmentViewModel.readSavedArticleFromVm().observe(requireActivity()) {
            for (i in it){
                saveList.add(i.title!!)
            }
        }

        readNewsViewModel = ViewModelProvider(this)[ReadNewsViewModel::class.java]
        binding.recyclerViewNLF.layoutManager = LinearLayoutManager(requireContext())
        mainViewModel.publicLivedata.observe(requireActivity()){
            adapter = NewsListAdapter(it.articles,this,savedFragmentViewModel,saveList)
            binding.recyclerViewNLF.adapter = adapter

        }
        binding.saveButtonNLF.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_newsListFragment_to_savedNewsFragment)
        }
        binding.logoutButton.setOnClickListener {
            session.setTheValue("out")
            val intent = Intent(requireActivity(),LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
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

    override fun newClick(position: Article) {
        val bundle = Bundle()
        bundle.putSerializable("ARTICLES",position)
        bundle.putBoolean("ARTICLE_FROM_NEWS_FRAGMENT",true)
        Navigation.findNavController(binding.root).navigate(R.id.action_newsListFragment_to_readNewsFragment,bundle)
    }
}