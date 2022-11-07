package com.example.shoppingapp.ui.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment(), OnProductClickListener {
    lateinit var adapter :ProductAdapter
    private lateinit var binding: FragmentProductsBinding
    private val viewModel by viewModels<ProductViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvProduct.adapter = adapter
        fun observerUiEvent() {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.productsList.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                    .collect { productEntities ->
                        adapter.submitList(productEntities)
                    }
            }
        }
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.rvProduct.adapter

    }



    override fun onProductClick(id: Int) {

    }

    override fun onFragmentItemClick(product: ProductEntity) {

    }


}

