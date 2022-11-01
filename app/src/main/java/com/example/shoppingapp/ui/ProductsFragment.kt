package com.example.shoppingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
import com.example.shoppingapp.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment(), OnProductClickListener {
    private lateinit var binding: FragmentProductsBinding
    private val viewModel by viewModels<ProductViewModel>()
    private val job = Job()
    private val coroutineJob = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        lifecycleScope.launchWhenResumed {

            launch {
                viewModel.uiEvent.collect { uiEvent ->
                    when (uiEvent) {
                        is PostViewEvent.ShowMessage -> {

                        }
                        is PostViewEvent.NavigateToDetail -> {
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onPostClick(product: ProductRecyclerViewItem.ProductDTO) {
        coroutineJob.launch {
            viewModel.onFavoritePost(product)
        }
    }

}
