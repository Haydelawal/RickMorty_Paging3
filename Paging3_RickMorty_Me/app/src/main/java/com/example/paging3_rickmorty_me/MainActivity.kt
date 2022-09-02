package com.example.paging3_rickmorty_me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paging3_rickmorty_me.adapter.RickMortyPagingAdapter
import com.example.paging3_rickmorty_me.databinding.ActivityMainBinding
import com.example.paging3_rickmorty_me.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //activity_main ==>> Activity_Main_Binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var rickMortyPagingAdapter: RickMortyPagingAdapter
    private val myViewModel: RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        loadData()

    }

    private fun loadData(){
        lifecycleScope.launch {
            myViewModel.listData.collect{ pagingData ->
                rickMortyPagingAdapter.submitData(pagingData)

            }
        }
    }

    private fun setUpRecyclerView(){
        rickMortyPagingAdapter = RickMortyPagingAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = rickMortyPagingAdapter
            setHasFixedSize(true)
        }

    }
}