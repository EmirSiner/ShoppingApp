package com.example.shoppingapp.ui.basket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.databinding.FragmentBasketItemBinding

class BasketAdapter (
    private val listener: BasketItemClickListener
    ) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){

    class BasketViewHolder(val binding: FragmentBasketItemBinding): RecyclerView.ViewHolder(binding.root)

    object CartDiffCallback: DiffUtil.ItemCallback<ProductEntity>(){
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem == newItem
        }

    }

    private val diffList = AsyncListDiffer(this,CartDiffCallback)
    var product: List<ProductEntity>
        get()=diffList.currentList
        set(value)=diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = FragmentBasketItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        with(holder){
            binding.tvTitle.text = product[position].title
            binding.tvPrice.text = "$"+product[position].price.toString()
            Glide.with(binding.ivCart)
                .load(product[position].image)
                .into(binding.ivCart)

            binding.deleteButton.setOnClickListener {
                listener.onItemClick(product[position])
                println(product[position].title)
            }
        }
    }

    override fun getItemCount(): Int {
        return product.size
    }

}

interface BasketItemClickListener {
    fun onItemClick(product: ProductEntity)
}