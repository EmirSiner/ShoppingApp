package com.example.shoppingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
import com.example.shoppingapp.databinding.ItemProductBinding

class ProductAdapter(private val listener: OnProductClickListener) : ListAdapter<ProductRecyclerViewItem.ProductDTO, ProductAdapter.ProductViewHolder>(ProductDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductRecyclerViewItem.ProductDTO, listener: OnProductClickListener) {
            binding.dataHolder = product
            binding.tvPrice.setOnClickListener {
                listener.onPostClick(product)
            }
            binding.executePendingBindings()
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<ProductRecyclerViewItem.ProductDTO>() {
        override fun areItemsTheSame(oldItem: ProductRecyclerViewItem.ProductDTO, newItem: ProductRecyclerViewItem.ProductDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductRecyclerViewItem.ProductDTO, newItem: ProductRecyclerViewItem.ProductDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnProductClickListener {
    fun onPostClick(product: ProductRecyclerViewItem.ProductDTO)
}
