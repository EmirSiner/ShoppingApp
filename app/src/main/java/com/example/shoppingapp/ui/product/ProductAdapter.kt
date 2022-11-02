package com.example.shoppingapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.model.product.ProductDTO
import com.example.shoppingapp.databinding.ItemProductBinding

class ProductAdapter(private val listener: OnProductClickListener) : ListAdapter<ProductDTO,
        ProductAdapter.ProductViewHolder>(ProductDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product:ProductDTO, listener: OnProductClickListener) {
            binding.dataHolder = product
            binding.tvPrice.setOnClickListener {
                listener.onPostClick(product)
            }
            binding.executePendingBindings()
        }
    }

    object ProductDiffUtil : DiffUtil.ItemCallback<ProductDTO>() {
        override fun areItemsTheSame(oldItem: ProductDTO, newItem:ProductDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem:ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem == newItem
        }
    }
    private val diffList = AsyncListDiffer(this,ProductDiffUtil)
    var product: List<ProductDTO>
        get()=diffList.currentList
        set(value)=diffList.submitList(value)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        with(holder){

            binding.tvTitle.text = product[position].title
            binding.tvPrice.text = "$"+product[position].price.toString()
            Glide.with(binding.ivHome)
                .load(product[position].image)
                .into(binding.ivHome)

            binding.addButton.setOnClickListener {
                listener.onPostClick(product[position])
            }


            holder.itemView.setOnClickListener {
                listener.onFragmentItemClick(product[position])
            }
        }
    }
}

interface OnProductClickListener {
    fun onPostClick(product: ProductDTO)
    fun onFragmentItemClick(product: ProductDTO)
}
