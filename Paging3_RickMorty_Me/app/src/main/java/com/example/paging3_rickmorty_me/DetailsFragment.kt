package com.example.paging3_rickmorty_me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.paging3_rickmorty_me.databinding.FragmentDetailsBinding
import com.example.paging3_rickmorty_me.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var _binding: FragmentDetailsBinding
    private val binding get() = _binding!!

    private val myViewModel: RickMortyViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.apply {
            Glide.with(requireContext())
                .load(args.rickMortyCharacter.image)
                .into(imageView)

            textView2.text = args.rickMortyCharacter.name
            textView3.text = args.rickMortyCharacter.gender
            textView4.text = args.rickMortyCharacter.species
            textView5.text = args.rickMortyCharacter.origin.nameOrigin
            textView6.text = args.rickMortyCharacter.status
            textView7.text = args.rickMortyCharacter.location.urlLocation


        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}