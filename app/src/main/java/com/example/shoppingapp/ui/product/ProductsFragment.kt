package com.example.shoppingapp.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductsFragment : Fragment(), OnProductClickListener {
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

             binding.rvProduct.adapter =  ProductAdapter(listener = this)

        observerUiEvent()
    }


    private fun observerUiEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
           viewModel.productsList.flowWithLifecycle(viewLifecycleOwner.lifecycle)
           .collect { productEntities ->
               (binding.rvProduct.adapter as ProductAdapter).submitList(productEntities)
             }
        }

    }

    override fun onProductClick(id: Int) {
        viewModel.productsList

        val action = ProductsFragmentDirections.actionProductsFragmentToBasketFragment(id)
        findNavController().navigate(action)

    }

    override fun onBasketItemClick(product: ProductEntity) {
        viewModel.onBasketItem(product)

    }

}

