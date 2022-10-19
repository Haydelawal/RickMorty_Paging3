package com.example.paging3_rickmorty_me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paging3_rickmorty_me.adapter.RickMortyPagingAdapter
import com.example.paging3_rickmorty_me.databinding.FragmentHomeBinding
import com.example.paging3_rickmorty_me.model.Character
import com.example.paging3_rickmorty_me.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), RickMortyPagingAdapter.ClickListener {


    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding!!

    private val rickMortyPagingAdapter: RickMortyPagingAdapter by lazy { RickMortyPagingAdapter(this) }

    private val myViewModel: RickMortyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        loadData()

        return binding.root
    }

    private fun loadData(){
        lifecycleScope.launch {
            myViewModel.getAllRickMorty.collect{ pagingData ->
                rickMortyPagingAdapter.submitData(pagingData)

            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = rickMortyPagingAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }

    override fun onMyItemClick(character: Character) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
        findNavController().navigate(action)
    }
}