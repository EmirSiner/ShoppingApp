package com.example.shoppingapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.databinding.ItemSearchViewBinding

class SearchAdapter () : ListAdapter<ProductEntity, SearchAdapter.SearchViewHolder>(SearchDiffUtil) {

        private object SearchDiffUtil : DiffUtil.ItemCallback<ProductEntity>() {

            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.id == newItem.id

            }


            override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem == newItem
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            return SearchViewHolder(
                ItemSearchViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        class SearchViewHolder(private val binding: ItemSearchViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(product: ProductEntity) {
                binding.dataHolder = product
                binding.executePendingBindings()
            }
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            val product = getItem(position)
            holder.bind(product)
        }

    }