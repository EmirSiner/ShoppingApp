package com.example.shoppingapp.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.databinding.ItemProductBinding


class ProductAdapter(
    private val listener: OnProductClickListener
) : ListAdapter<ProductEntity, ProductAdapter.ProductViewHolder>(ProductDiffUtil) {

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
        val product = getItem(position)
        holder.bind(product, listener)
    }

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity, listener: OnProductClickListener) {
            binding.dataHolder = product
            binding.listener = listener
            binding.executePendingBindings()
        }
    }


    object ProductDiffUtil : DiffUtil.ItemCallback<ProductEntity>() {

        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnProductClickListener {
    fun onProductClick(id: Int)
    fun onFragmentItemClick(product: ProductEntity)
}