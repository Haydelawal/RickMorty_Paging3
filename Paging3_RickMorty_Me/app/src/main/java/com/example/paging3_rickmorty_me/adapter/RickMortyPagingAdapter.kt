package com.example.paging3_rickmorty_me.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.paging3_rickmorty_me.databinding.RickMortyLayoutBinding
import com.example.paging3_rickmorty_me.model.Character
import  com.example.paging3_rickmorty_me.adapter.RickMortyPagingAdapter.MyViewHolder

class RickMortyPagingAdapter(
    private val listener: ClickListener
) : PagingDataAdapter<Character, MyViewHolder>(diffCallBack) {

    inner class MyViewHolder( val binding: RickMortyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {
            textView.text = currentItem?.name

            Glide.with(holder.itemView)
                .load(currentItem?.image)
                .into(imageView)

//            val imageLink = currentItem?.image
//
//            imageView.load(imageLink){
//                crossfade(true)
//                crossfade(1000)

            }
        holder.itemView.setOnClickListener {
            listener.onMyItemClick(currentItem!!)
        }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RickMortyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    interface ClickListener {
        // item on click listener
        fun onMyItemClick(character: Character)
    }

}


